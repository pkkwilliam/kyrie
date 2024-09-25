package mo.bitcode.kyrie.service.game.model;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateType;

@Builder
@Data
public class GameEndedDto {

  private int awayTeamScore;
  private String gameId;
  private int homeTeamScore;
  private ScoreCalculateType scoreCalculateType;

}
