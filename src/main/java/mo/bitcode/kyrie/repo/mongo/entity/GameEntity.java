package mo.bitcode.kyrie.repo.mongo.entity;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import mo.bitcode.kyrie.service.game.model.GameStatus;
import mo.bitcode.kyrie.service.game.model.GameType;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateType;
import mo.bitcode.kyrie.service.team.model.TeamType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(value = "game")
public class GameEntity extends KyrieTableBase {

  private ObjectId awayTeamId;
  private int awayTeamScore;
  private ObjectId homeTeamId;
  private int homeTeamScore;
  private GameType gameType;
  private GameStatus gameStatus;
  private ScoreCalculateType scoreCalculateType;
  private TeamType teamType;

}
