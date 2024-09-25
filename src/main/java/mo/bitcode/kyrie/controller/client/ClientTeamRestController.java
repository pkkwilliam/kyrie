package mo.bitcode.kyrie.controller.client;

import mo.bitcode.kyrie.service.team.model.TeamDetailDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.controller.model.GetTeamsResponse;
import mo.bitcode.kyrie.facade.TeamFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/team/v1")
public class ClientTeamRestController {

  @Autowired
  private TeamFacade teamFacade;

  @GetMapping("/teams")
  public ResponseEntity<GetTeamsResponse> getTeams() {
    final List<TeamDetailDto> teams = this.teamFacade.getTeamByUser();
    final GetTeamsResponse getTeamsResponse = GetTeamsResponse.builder()
      .teams(teams)
      .build();
    return ResponseEntity.ok(getTeamsResponse);
  }

}
