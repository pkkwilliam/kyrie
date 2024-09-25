package mo.bitcode.kyrie.facade;

import mo.bitcode.kyrie.service.team.model.TeamDetailDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;

import java.util.List;

public interface TeamFacade {

  List<TeamDetailDto> getTeamByUser();

}
