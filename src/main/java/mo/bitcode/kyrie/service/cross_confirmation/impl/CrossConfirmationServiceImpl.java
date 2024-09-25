package mo.bitcode.kyrie.service.cross_confirmation.impl;

import mo.bitcode.kyrie.common.exception.KyrieException;
import mo.bitcode.kyrie.common.model.TeamSide;
import mo.bitcode.kyrie.common.util.CommonUtil;
import mo.bitcode.kyrie.repo.mongo.entity.ConveneCcEntity;
import mo.bitcode.kyrie.repo.mongo.entity.CoordinateCcEntity;
import mo.bitcode.kyrie.repo.mongo.entity.ScoreCcEntity;
import mo.bitcode.kyrie.service.cross_confirmation.CrossConfirmationService;
import mo.bitcode.kyrie.service.cross_confirmation.CrossConfirmationTransformer;
import mo.bitcode.kyrie.service.cross_confirmation.model.CrossConfirmationBase;
import mo.bitcode.kyrie.service.cross_confirmation.model.ConveneCcDto;
import mo.bitcode.kyrie.service.cross_confirmation.model.CoordinateCcDto;
import mo.bitcode.kyrie.service.cross_confirmation.model.ScoreCcDto;
import mo.bitcode.kyrie.service.cross_confirmation.repo.ConveneCcRepository;
import mo.bitcode.kyrie.service.cross_confirmation.repo.CoordinateCcRepository;
import mo.bitcode.kyrie.service.cross_confirmation.repo.ScoreCcRepository;
import mo.bitcode.kyrie.service.score.ScoreService;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateResult;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CrossConfirmationServiceImpl implements CrossConfirmationService {

  private ConveneCcRepository conveneRepository;
  private CoordinateCcRepository coordinateRepository;
  private ScoreCcRepository scoreCcRepository;
  private ScoreService scoreService;

  public CrossConfirmationServiceImpl(ConveneCcRepository conveneRepository,
                                      CoordinateCcRepository coordinateRepository,
                                      ScoreCcRepository scoreCcRepository,
                                      ScoreService scoreService) {
    this.conveneRepository = conveneRepository;
    this.coordinateRepository = coordinateRepository;
    this.scoreCcRepository = scoreCcRepository;
    this.scoreService = scoreService;
  }

  @Override
  public ConveneCcDto initConveneCc(String gameId, String homeTeamId, String awayTeamId) {
    final ConveneCcEntity entity = new ConveneCcEntity();
    this.setValueForNewInstance(entity, gameId, homeTeamId, awayTeamId);
    final ConveneCcEntity persistedEntity = this.conveneRepository.create(entity);
    return this.getCcTransformer().transform(persistedEntity);
  }

  @Override
  public CoordinateCcDto initCoordinateCc(String gameId, String homeTeamId, String awayTeamId) {
    final CoordinateCcEntity entity = new CoordinateCcEntity();
    this.setValueForNewInstance(entity, gameId, homeTeamId, awayTeamId);
    final CoordinateCcEntity persistedEntity = this.coordinateRepository.create(entity);
    return this.getCcTransformer().transform(persistedEntity);
  }

  @Override
  public ScoreCcDto initScoreCc(String gameId, String homeTeamId, String awayTeamId) {
    final ScoreCcEntity entity = new ScoreCcEntity();
    this.setValueForNewInstance(entity, gameId, homeTeamId, awayTeamId);
    final ScoreCcEntity persistedEntity = this.scoreCcRepository.create(entity);
    return this.getCcTransformer().transform(persistedEntity);
  }

  @Override
  public ConveneCcDto updateConveneCc(String gameId, String teamId, ConveneCcDto dto) {
    final ConveneCcEntity persistedEntity = this.conveneRepository.getByGameId(new ObjectId(gameId));
    this.updateCcValue(teamId, persistedEntity, dto);
    final ConveneCcEntity updatedEntity = this.conveneRepository.update(persistedEntity);
    return this.getCcTransformer().transform(updatedEntity);
  }

  @Override
  public CoordinateCcDto updateCoordinateCc(String gameId, String teamId, CoordinateCcDto dto) {
    final CoordinateCcEntity persistedEntity = this.coordinateRepository.getByGameId(new ObjectId(gameId));
    this.updateCcValue(teamId, persistedEntity, dto);
    final CoordinateCcEntity updatedEntity = this.coordinateRepository.update(persistedEntity);
    return this.getCcTransformer().transform(updatedEntity);
  }

  @Override
  public ScoreCcDto updateScoreCc(String gameId, String teamId, ScoreCcDto dto) {
    final ScoreCcEntity persistedEntity = this.scoreCcRepository.getByGameId(new ObjectId(gameId));
    if (persistedEntity.isHomeTeamFinalConfirm() && persistedEntity.isAwayTeamFinalConfirm()) {
      // TODO fix exception
      throw new KyrieException(HttpStatus.INTERNAL_SERVER_ERROR, "500.999", "Score already finalize");
    }
    this.updateCcValue(teamId, persistedEntity, dto);

    // calculate to see if matching, if match then we should be done
    final ScoreCalculateResult scoreCalculateResult = this.scoreService.calculateScore(
      persistedEntity.getHomeTeamAssert(), persistedEntity.getAwayTeamAssert());

    final ScoreCcEntity updatedEntity = this.scoreCcRepository.update(persistedEntity);
    // TODO check score is matching or not and how many times been updated
    return this.getCcTransformer().transform(updatedEntity, scoreCalculateResult);
  }

  protected void setValueForNewInstance(CrossConfirmationBase<ObjectId> toPersistedEntity, String gameId, String homeTeamId, String awayTeamId) {
    toPersistedEntity.setGameId(new ObjectId(gameId));
    toPersistedEntity.setAwayTeamId(new ObjectId(awayTeamId));
    toPersistedEntity.setHomeTeamId(new ObjectId(homeTeamId));
  }

  protected void updateCcValue(String teamId, CrossConfirmationBase<ObjectId> entity, CrossConfirmationBase<String> dto) {
    // set value into persisted only in case user maliciously alter other team's confirmation detail
    final TeamSide teamSide =
      CommonUtil.getSide(entity.getHomeTeamId(), entity.getAwayTeamId(), teamId);
    if (teamSide == TeamSide.HOME) {
      entity.setHomeTeamPreConfirm(dto.isHomeTeamPreConfirm());
      entity.setHomeTeamFinalConfirm(dto.isHomeTeamFinalConfirm());
    } else {
      entity.setAwayTeamPreConfirm(dto.isAwayTeamPreConfirm());
      entity.setAwayTeamFinalConfirm(dto.isAwayTeamFinalConfirm());
    }
  }

  protected CrossConfirmationTransformer getCcTransformer() {
    return new CrossConfirmationTransformer();
  }

}
