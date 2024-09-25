package mo.bitcode.kyrie.service.cross_confirmation.model;

import lombok.Data;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateResult;

@Data
public class ScoreCcDto extends CrossConfirmationBase<String> {

  private TeamAssertScore awayTeamAssert;
  private TeamAssertScore homeTeamAssert;
  private ScoreCalculateResult scoreCalculateResult;

}
