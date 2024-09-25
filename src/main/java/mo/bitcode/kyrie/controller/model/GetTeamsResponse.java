package mo.bitcode.kyrie.controller.model;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.kyrie.service.team.model.TeamDetailDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;

import java.util.List;

@Builder
@Data
public class GetTeamsResponse {

  private List<TeamDetailDto> teams;

}
