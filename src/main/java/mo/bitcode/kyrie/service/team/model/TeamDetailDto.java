package mo.bitcode.kyrie.service.team.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import mo.bitcode.kyrie.common.model.dto.UserProfileDto;
import mo.bitcode.kyrie.service.game.model.GameType;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
public class TeamDetailDto {

  private String id;
  private Set<GameType> allowToJoinGameType;
  private UserProfileDto captain;
  private String name;
  private int rating;
  private int season;
  private TeamStatus status;
  private TeamType teamType;
  private List<UserProfileDto> teamMembers;

}
