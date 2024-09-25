package mo.bitcode.kyrie.service.team.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class TeamDto {

  private String id;
  private String captainId;
  private String name;
  private int rating;
  private int season;
  private TeamStatus teamStatus;
  private TeamType teamType;
  private List<String> teamMemberIds;

}
