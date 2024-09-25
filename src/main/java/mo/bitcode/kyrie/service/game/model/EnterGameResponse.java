package mo.bitcode.kyrie.service.game.model;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import org.bson.types.ObjectId;

@Builder
@Data
public class EnterGameResponse {

  private TeamDto awayTeam;
  private EnterGameResult enterGameResult;
  private String gameId;
  private TeamDto homeTeam;

}
