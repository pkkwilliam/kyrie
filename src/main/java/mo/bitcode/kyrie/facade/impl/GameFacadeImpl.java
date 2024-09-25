package mo.bitcode.kyrie.facade.impl;

import mo.bitcode.kyrie.common.bedrock.KyrieApplicationComponent;
import mo.bitcode.kyrie.common.util.CommonUtil;
import mo.bitcode.kyrie.facade.GameFacade;
import mo.bitcode.kyrie.facade.handler.GameCrossConfirmFinalizeHandler;
import mo.bitcode.kyrie.facade.handler.GameCrossConfirmationUpdateHandler;
import mo.bitcode.kyrie.service.chat.ChatService;
import mo.bitcode.kyrie.service.chat.model.ChatGroupInfo;
import mo.bitcode.kyrie.service.credit.CreditService;
import mo.bitcode.kyrie.service.cross_confirmation.CrossConfirmationService;
import mo.bitcode.kyrie.service.cross_confirmation.model.*;
import mo.bitcode.kyrie.service.game.GameService;
import mo.bitcode.kyrie.service.game.model.*;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateResult;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateType;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.team.model.TeamType;
import mo.bitcode.kyrie.service.web_socket.KyrieWebSocketService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameFacadeImpl extends KyrieApplicationComponent implements GameFacade {

  private static final Logger LOGGER = LoggerFactory.getLogger(GameFacadeImpl.class);
  private ChatService chatService;
  private CreditService creditService;
  private CrossConfirmationService crossConfirmationService;
  private GameService gameService;
  private TeamService teamService;
  private KyrieWebSocketService kyrieWebSocketService;

  public GameFacadeImpl(ChatService chatService,
                        CreditService creditService,
                        CrossConfirmationService crossConfirmationService,
                        GameService gameService,
                        TeamService teamService,
                        KyrieWebSocketService kyrieWebSocketService) {
    this.chatService = chatService;
    this.creditService = creditService;
    this.crossConfirmationService = crossConfirmationService;
    this.gameService = gameService;
    this.teamService = teamService;
    this.kyrieWebSocketService = kyrieWebSocketService;
  }

  /*
   * 1. Check if user is captain
   * 2. All User credit Score enough
   * 3. Any current team member are in other queue
   * 4. Find game if there are any match
   *  return match result
   *  if match.fail -> add into DB
   *  if match.success -> call this.onGameMatched()
   */
  @Override
  public EnterGameResponse findGame(String teamId, EnterGameRequest enterGameRequest) {
    final GameType gameType = GameType.RATING;
    final ObjectId userId = this.getKyrieUserPrinciple();
    final TeamDto teamDto = this.teamService.getDto(teamId);
    final List<String> teamMembers = teamDto.getTeamMemberIds();

    // Step 1
    this.teamService.validateUserIsCaptain(userId, teamDto);

    // Step 2
    this.creditService.validateUsersAllowToJoinGame(gameType, teamMembers);
    final TeamType teamType = teamDto.getTeamType();

    // Step 3
    this.gameService.validateUserNotInOtherGameOrQueue(teamMembers);

    // Step 4
    final EnterGameResponse enterGameResponse = this.gameService.enterFindGame(gameType, teamDto, enterGameRequest.getAllowLocationIds());
    if (enterGameResponse.getEnterGameResult() == EnterGameResult.MATCHED) {
      this.onGameMatched(enterGameResponse.getGameId(),
        enterGameResponse.getHomeTeam(),
        enterGameResponse.getAwayTeam());
    }
    return enterGameResponse;
  }

  @Override
  public ConveneCcDto updateGameConvene(String gameId, String teamId, ConveneCcDto dto) {
    final GameCrossConfirmationUpdateHandler<ConveneCcDto> updateHandler =
      () -> this.crossConfirmationService.updateConveneCc(gameId, teamId, dto);
    final GameCrossConfirmFinalizeHandler finalizedHandler = () -> onConveneFinalize(gameId);
    return this.updateCrossConfirmation(teamId, updateHandler, finalizedHandler);
  }

  @Override
  public ScoreCcDto updateScore(String gameId, String teamId, ScoreCcDto dto) {
    // Update result and have the result calculated
    final ScoreCcDto updatedDto = this.updateCrossConfirmation(teamId,
      () -> this.crossConfirmationService.updateScoreCc(gameId, teamId, dto));

    // process depends on types
    final ScoreCalculateResult result = updatedDto.getScoreCalculateResult();
    final ScoreCalculateType calculateType = result.getScoreCalculateType();

    switch (calculateType) {
      case RESULT_DISCREPANCY_RETRY:
        // TODO alert message to user for retry
        break;
      case AGREE:
      case SCORE_DISCREPANCY: // We can consider this after finish for now. Might want to do something for V2
      case RESULT_CONFLICT_FORCE_CALCULATED: // nothing else we can do since both party assert different result.
        this.onScoreFinalize(gameId, calculateType, result.getHomeTeamScore(), result.getAwayTeamScore());
        break;
    }
    return updatedDto;
  }

  @Override
  public CoordinateCcDto updateGameCoordinate(String gameId, String teamId, CoordinateCcDto dto) {
    final GameCrossConfirmationUpdateHandler<CoordinateCcDto> updateHandler =
      () -> this.crossConfirmationService.updateCoordinateCc(gameId, teamId, dto);
    final GameCrossConfirmFinalizeHandler finalizedHandler = () -> onCoordinateFinalize(gameId);
    return this.updateCrossConfirmation(teamId, updateHandler, finalizedHandler);
  }

  protected <T extends CrossConfirmationBase<String>> T updateCrossConfirmation(String teamId,
                                                                                GameCrossConfirmationUpdateHandler<T> updateHandler) {
    this.teamService.validateUserIsCaptain(this.getKyrieUserPrinciple(), teamId);
    return updateHandler.update();
  }

  protected <T extends CrossConfirmationBaseDto> T updateCrossConfirmation(String teamId,
                                                                           GameCrossConfirmationUpdateHandler<T> updateHandler,
                                                                           GameCrossConfirmFinalizeHandler finalizeHandler) {
    final T updatedCrossConfirmation = this.updateCrossConfirmation(teamId, updateHandler);
    final CrossConfirmationResult result = updatedCrossConfirmation.getResult();
    if (result == CrossConfirmationResult.PRE_CONFIRMED_PENDING || result == CrossConfirmationResult.CONFIRMED_PENDING) {
      // TODO send web socket to user
      // TODO send notification to user
    } else if (result == CrossConfirmationResult.CONFIRMED) {
      finalizeHandler.onCrossConfirmFinalized();
    }
    return updatedCrossConfirmation;
  }

  public void onConveneFinalize(String gameId) {
    final GameDto gameDto = this.gameService.get(gameId);
    this.crossConfirmationService.initConveneCc(gameId,
      gameDto.getHomeTeamDto().getId(),
      gameDto.getAwayTeamDto().getId());
    // TODO send web socket to users
    // TODO send notification to users
  }

  public void onCoordinateFinalize(String gameId) {
    final GameDto gameDto = this.gameService.get(gameId);
    this.crossConfirmationService.initConveneCc(gameId,
      gameDto.getHomeTeamDto().getId(),
      gameDto.getAwayTeamDto().getId());
    // TODO send web socket to users
    // TODO send notification to users
  }

  public void onScoreFinalize(String gameId,
                              ScoreCalculateType scoreCalculateType,
                              int homeTeamScore,
                              int awayTeamScore) {
    final GameEndedDto gameEndedDto = GameEndedDto.builder()
      .awayTeamScore(awayTeamScore)
      .homeTeamScore(homeTeamScore)
      .gameId(gameId)
      .scoreCalculateType(scoreCalculateType)
      .build();
    final GameDto gameDto = this.gameService.onGameEnded(gameEndedDto);

    // TODO Future Optimize: this can be a sync kafka service
    this.creditService.processCreditOnGameEnd(gameDto);
  }

  public void onGameMatched(String gameId, TeamDto homeTeam, TeamDto awayTeam) {
    final List<String> toUserIds = CommonUtil.getUserIds(homeTeam, awayTeam);
    final String groupName = String.format("%s vs %s", homeTeam.getName(), awayTeam.getName());

    // Create Game Discuss Cross Confirmation on Time and Location
    this.crossConfirmationService.initCoordinateCc(gameId, homeTeam.getId(), awayTeam.getId());

    this.kyrieWebSocketService.sendAlertMessages(toUserIds, GameStatus.DISCUSS_MEETING_LOCATION_AND_TIME);

    final ChatGroupInfo chatGroupInfo = this.chatService.createGroupChat(groupName, toUserIds);
    this.chatService.sendAdminMessageToGroup(chatGroupInfo.getGroupId(), "Team Matched");
    this.chatService.sendAdminMessageToGroup(chatGroupInfo.getGroupId(), "Both team can discuss where and when should meet and start the game");
  }

}
