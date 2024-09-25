package mo.bitcode.kyrie.facade.impl;

import mo.bitcode.kyrie.facade.FacadeTemplate;
import mo.bitcode.kyrie.service.team.model.TeamDetailDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.common.model.dto.UserProfileDto;
import mo.bitcode.kyrie.facade.TeamFacade;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TeamFacadeImpl extends FacadeTemplate implements TeamFacade {

  private TeamService teamService;

  public TeamFacadeImpl(KyrieUserProfileService kyrieUserProfileService, TeamService teamService) {
    super(kyrieUserProfileService);
    this.teamService = teamService;
  }

  @Override
  public List<TeamDetailDto> getTeamByUser() {
    final String currentUserId = this.getUserPrinciple();

    // Step 1. get all team by userId
    final List<TeamDto> teamEntities = this.teamService.getUserTeams(currentUserId);

    // Step 2. get all user details
    final List<String> toRetrieveUserProfileIds = teamEntities.stream()
      .flatMap(subTeams -> subTeams.getTeamMemberIds().stream())
      .distinct()
      .collect(Collectors.toList());

    final Map<String, UserProfileDto> userIdUserProfile = this.getUserProfileDtos(toRetrieveUserProfileIds);
    final List<TeamDetailDto> teamDetailDtos = this.transformTeamDto(teamEntities, userIdUserProfile);
    return teamDetailDtos;
  }

}
