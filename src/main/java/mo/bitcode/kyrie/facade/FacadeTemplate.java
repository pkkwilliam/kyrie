package mo.bitcode.kyrie.facade;

import mo.bitcode.kyrie.common.bedrock.KyrieApplicationComponent;
import mo.bitcode.kyrie.common.model.dto.UserProfileDto;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.team.TeamTransformer;
import mo.bitcode.kyrie.service.team.model.TeamDetailDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class FacadeTemplate extends KyrieApplicationComponent {

  private KyrieUserProfileService kyrieUserProfileService;
  private TeamTransformer teamTransformer;

  public FacadeTemplate(KyrieUserProfileService kyrieUserProfileService) {
    this.kyrieUserProfileService = kyrieUserProfileService;
    this.teamTransformer = new TeamTransformer();
  }

  protected Map<String, UserProfileDto> getUserProfileDtos(List<String> ids) {
    final List<UserProfile> userProfiles =
      this.kyrieUserProfileService.getUserProfileByIds(ids);
    final Map<String, UserProfileDto> objectIdUserProfiles = userProfiles.stream()
      .map(UserProfileDto::new)
      .collect(Collectors.toMap(UserProfileDto::getId, Function.identity()));
    return objectIdUserProfiles;
  }

  protected List<TeamDetailDto> transformTeamDto(List<TeamDto> teams, Map<String, UserProfileDto> userIdUserProfile) {
    return teams.stream()
      .map(team -> this.teamTransformer.transform(team, userIdUserProfile))
      .collect(Collectors.toList());
  }

}
