package mo.bitcode.kyrie.service.game.impl;

import mo.bitcode.kyrie.common.bedrock.KyrieApplicationComponent;
import mo.bitcode.kyrie.repo.mongo.GameMatchingRepository;
import mo.bitcode.kyrie.repo.mongo.GameRepository;
import mo.bitcode.kyrie.repo.mongo.entity.GameEntity;
import mo.bitcode.kyrie.repo.mongo.entity.GameMatchingEntity;
import mo.bitcode.kyrie.service.game.GameService;
import mo.bitcode.kyrie.service.game.GameTransformer;
import mo.bitcode.kyrie.service.game.model.*;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.team.model.TeamType;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl extends KyrieApplicationComponent implements GameService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GameServiceImpl.class);
  private GameRepository gameRepository;
  private GameMatchingRepository gameMatchingRepository;
  private TeamService teamService;

  public GameServiceImpl(GameRepository gameRepository, GameMatchingRepository gameMatchingRepository, TeamService teamService) {
    this.gameRepository = gameRepository;
    this.gameMatchingRepository = gameMatchingRepository;
    this.teamService = teamService;
  }

  @Override
  public EnterGameResponse enterFindGame(GameType gameType, TeamDto teamDto, List<String> availableLocations) {
    final TeamType teamType = teamDto.getTeamType();
    final List<GameMatchingEntity> matchedEntities = this.gameMatchingRepository
      .findAllByGameTypeAndTeamTypeAndAndAvailableLocationIdsIn(gameType, teamType, availableLocations);
    final boolean hasMatch = !matchedEntities.isEmpty();

    if (!hasMatch) {
      // TODO add mechanism to delete after 2 hour
      this.addTeamIntoGameMatching(gameType, teamType, teamDto, availableLocations);
      return EnterGameResponse.builder()
        .enterGameResult(EnterGameResult.QUEUE)
        .build();
    }
    // Calculate which to match if matched > 1
    final GameMatchingEntity matchedHomeTeam = this.getToMatchEntity(matchedEntities);
    final TeamDto homeTeam = this.teamService.getDto(matchedHomeTeam.getTeamId());

    // create a active game
    // TODO think if there it failed when trying to create game, how to rematch the people from pool.
    final EnterGameResponse response = this.onGameMatched(gameType, teamType, homeTeam, teamDto);

    // remove the matched game from the GameMatching DB
    this.removeTeamFromGameMatching(matchedHomeTeam.getTeamId());
    return response;
  }

  @Override
  public GameDto get(String gameId) {
    return null;
  }

  @Override
  public GameDto onGameEnded(GameEndedDto gameEndedDto) {
    final GameEntity gameEntity = this.gameRepository.get(gameEndedDto.getGameId());
    final List<TeamDto> teamDtos = this.teamService.getTeamsByRawIds(List.of(gameEntity.getAwayTeamId(), gameEntity.getHomeTeamId()));
    gameEntity.setAwayTeamScore(gameEndedDto.getAwayTeamScore());
    gameEntity.setHomeTeamScore(gameEntity.getHomeTeamScore());
    gameEntity.setScoreCalculateType(gameEndedDto.getScoreCalculateType());
    final GameEntity updateGameEntity = this.gameRepository.save(gameEntity);
    return this.getTransformer().transform(updateGameEntity, teamDtos);
  }

  @Override
  public void validateUserNotInOtherGameOrQueue(List<String> userIds) {

  }

  protected void addTeamIntoGameMatching(GameType gameType, TeamType teamType, TeamDto teamDto, List<String> availableLocations) {
    final GameMatchingEntity toSaveGameMatchingEntity = GameMatchingEntity.builder()
      .teamId(new ObjectId(teamDto.getId()))
      .availableLocationIds(availableLocations)
      .gameType(gameType)
      .teamType(teamDto.getTeamType())
      .build();
    this.gameMatchingRepository.save(toSaveGameMatchingEntity);
  }

  /**
   *
   * @param homeTeamId the first team was join, the one in was found in DB and matched
   * @param awayTeamId the second who join later
   */
  protected GameEntity createGame(GameType gameType, TeamType teamType, ObjectId homeTeamId, ObjectId awayTeamId) {
    final GameEntity gameEntity = GameEntity.builder()
      .awayTeamId(awayTeamId)
      .homeTeamId(homeTeamId)
      .gameType(gameType)
      .gameStatus(GameStatus.DISCUSS_MEETING_LOCATION_AND_TIME)
      .teamType(teamType)
      .build();
    return this.gameRepository.save(gameEntity);
  }

  protected GameMatchingEntity getToMatchEntity(List<GameMatchingEntity> gameMatchingEntities) {
    return gameMatchingEntities.stream()
      .sorted((g1, g2) -> g2.getCreateTime().compareTo(g1.getCreateTime()))
      .findFirst()
      .get();
  }

  public EnterGameResponse onGameMatched(GameType gameType,
                                         TeamType teamType,
                                         TeamDto homeTeam,
                                         TeamDto awayTeam) {
    final GameEntity createdGame = this.createGame(gameType, teamType,
      new ObjectId(homeTeam.getId()), new ObjectId(awayTeam.getId()));
    return EnterGameResponse.builder()
      .enterGameResult(EnterGameResult.MATCHED)
      .homeTeam(homeTeam)
      .awayTeam(awayTeam)
      .gameId(createdGame.getId().toString())
      .build();
  }

  protected GameTransformer getTransformer() {
    return new GameTransformer();
  }

  protected void removeTeamFromGameMatching(ObjectId toRemoveTeamObjectId) {
    this.gameMatchingRepository.deleteByTeamId(toRemoveTeamObjectId);
  }

}
