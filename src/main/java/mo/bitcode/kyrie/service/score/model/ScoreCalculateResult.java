package mo.bitcode.kyrie.service.score.model;

import lombok.Data;

@Data
public class ScoreCalculateResult {

  private int awayTeamScore;
  private int homeTeamScore;
  private ScoreCalculateType scoreCalculateType;

}
