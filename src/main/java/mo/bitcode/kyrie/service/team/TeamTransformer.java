package mo.bitcode.kyrie.service.team;

import mo.bitcode.kyrie.common.model.dto.UserProfileDto;
import mo.bitcode.kyrie.common.util.CommonUtil;
import mo.bitcode.kyrie.repo.mongo.entity.TeamEntity;
import mo.bitcode.kyrie.service.game.model.GameType;
import mo.bitcode.kyrie.service.team.model.TeamDetailDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TeamTransformer {

  public TeamDetailDto transform(TeamDto teamDto, Map<String, UserProfileDto> userIdUserProfile) {
    final List<UserProfileDto> userProfileDtos = teamDto.getTeamMemberIds().stream()
      .map(member -> userIdUserProfile.get(member.toString()))
      .collect(Collectors.toList());
    final TeamDetailDto teamDetailDto = new TeamDetailDto();
    teamDetailDto.setId(teamDto.getId());
    teamDetailDto.setAllowToJoinGameType(this.getAllowToJoinGameType(teamDto));
    teamDetailDto.setCaptain(userIdUserProfile.get(teamDto.getCaptainId()));
    teamDetailDto.setName(teamDto.getName());
    teamDetailDto.setRating(teamDto.getRating());
    teamDetailDto.setSeason(teamDto.getSeason());
    teamDetailDto.setStatus(teamDto.getTeamStatus());
    teamDetailDto.setTeamType(teamDto.getTeamType());
    teamDetailDto.setTeamMembers(userProfileDtos);
    return teamDetailDto;
  }

  public List<TeamDto> transform(List<TeamEntity> teamEntities) {
    return teamEntities.stream().map(this::transform).collect(Collectors.toList());
  }

  public TeamDto transform(TeamEntity teamEntity) {
    if (teamEntity == null) {
      return null;
    }
    final TeamDto teamDto = new TeamDto();
    teamDto.setId(teamEntity.getId().toString());
    teamDto.setCaptainId(teamEntity.getCaptainId().toString());
    teamDto.setName(teamEntity.getName());
    teamDto.setRating(teamEntity.getRating());
    teamDto.setSeason(teamEntity.getSeason());
    teamDto.setTeamStatus(teamEntity.getStatus());
    teamDto.setTeamType(teamEntity.getTeamType());
    teamDto.setTeamMemberIds(CommonUtil.toString(teamEntity.getTeamMemberIds()));
    return teamDto;
  }

  private Set<GameType> getAllowToJoinGameType(TeamDto teamDto) {
    final Set<GameType> allowToJoinGames = new HashSet<>();
    allowToJoinGames.add(GameType.RATING);
    return allowToJoinGames;
  }

}
