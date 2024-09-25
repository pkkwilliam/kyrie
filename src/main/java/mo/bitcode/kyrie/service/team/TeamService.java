package mo.bitcode.kyrie.service.team;

import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.team.model.TeamType;
import org.bson.types.ObjectId;

import java.util.List;

public interface TeamService {

  TeamDto create(ObjectId captainId, TeamType teamType, String name, List<ObjectId> teamMemberIds);
  TeamDto getDto(String teamId);
  TeamDto getDto(ObjectId teamId);
  List<TeamDto> getTeams(List<String> teamIds);
  List<TeamDto> getTeamsByRawIds(List<ObjectId> teamIds);
  List<TeamDto> getTeamsByUserIds(List<String> userIds);
  List<TeamDto> getUserTeams(String userId);
  void validateUserIsCaptain(ObjectId userId, TeamDto teamDto);
  void validateUserIsCaptain(ObjectId userId, String teamId);

}
