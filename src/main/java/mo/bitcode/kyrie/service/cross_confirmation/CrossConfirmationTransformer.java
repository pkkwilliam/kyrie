package mo.bitcode.kyrie.service.cross_confirmation;

import mo.bitcode.kyrie.repo.mongo.entity.ConveneCcEntity;
import mo.bitcode.kyrie.repo.mongo.entity.CoordinateCcEntity;
import mo.bitcode.kyrie.repo.mongo.entity.ScoreCcEntity;
import mo.bitcode.kyrie.service.cross_confirmation.model.*;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateResult;
import org.bson.types.ObjectId;

public class CrossConfirmationTransformer {

  public ConveneCcDto transform(ConveneCcEntity entity) {
    final ConveneCcDto result = new ConveneCcDto();
    this.setCcBaseDtoValue(entity, result);
    return result;
  }

  public CoordinateCcDto transform(CoordinateCcEntity entity) {
    final CoordinateCcDto result = new CoordinateCcDto();
    this.setCcBaseDtoValue(entity, result);
    result.setProposeCourtId(entity.getProposeCourtId().toString());
    result.setProposeDateTime(entity.getProposeDateTime());
    return result;
  }

  public ScoreCcDto transform(ScoreCcEntity entity) {
    return this.transform(entity, null);
  }

  public ScoreCcDto transform(ScoreCcEntity entity, ScoreCalculateResult scoreCalculateResult) {
    final ScoreCcDto result = new ScoreCcDto();
    this.setCcBaseValue(entity, result);
    result.setAwayTeamAssert(entity.getAwayTeamAssert());
    result.setHomeTeamAssert(entity.getHomeTeamAssert());
    result.setScoreCalculateResult(scoreCalculateResult);
    return result;
  }

  protected void setCcBaseDtoValue(CrossConfirmationBase<ObjectId> entity, CrossConfirmationBaseDto dto) {
    this.setCcBaseValue(entity, dto);
    dto.setResult(this.generateConfirmResult(dto));
  }

  protected void setCcBaseValue(CrossConfirmationBase<ObjectId> entity, CrossConfirmationBase<String> dto) {
    dto.setAwayTeamId(entity.getAwayTeamId().toString());
    dto.setAwayTeamPreConfirm(entity.isAwayTeamPreConfirm());
    dto.setAwayTeamFinalConfirm(entity.isAwayTeamFinalConfirm());
    dto.setGameId(entity.getGameId().toString());
    dto.setHomeTeamId(entity.getHomeTeamId().toString());
    dto.setHomeTeamPreConfirm(entity.isHomeTeamPreConfirm());
    dto.setHomeTeamFinalConfirm(entity.isHomeTeamFinalConfirm());
  }

  protected <T> CrossConfirmationResult generateConfirmResult(CrossConfirmationBase<T> crossConfirmationBase) {
    if (!crossConfirmationBase.isAwayTeamPreConfirm() || !crossConfirmationBase.isHomeTeamPreConfirm()) {
      return CrossConfirmationResult.PRE_CONFIRMED_PENDING;
    } else if (!crossConfirmationBase.isAwayTeamFinalConfirm() || !crossConfirmationBase.isHomeTeamFinalConfirm()) {
      return CrossConfirmationResult.CONFIRMED_PENDING;
    } else {
      return CrossConfirmationResult.CONFIRMED;
    }
  }

}
