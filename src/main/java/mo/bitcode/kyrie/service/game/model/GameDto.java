package mo.bitcode.kyrie.service.game.model;

import lombok.Data;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateType;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.team.model.TeamType;

@Data
public class GameDto {

  private String id;
  private TeamDto awayTeamDto;
  private int awayTeamScore;
  private TeamDto homeTeamDto;
  private int homeTeamScore;
  private GameType gameType;
  private GameStatus gameStatus;
  private ScoreCalculateType scoreCalculateType;
  private TeamType teamType;

}
