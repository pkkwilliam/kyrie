package mo.bitcode.kyrie.service.team;

import mo.bitcode.kyrie.common.bedrock.rest_template.KyrieMongoAbstractServiceTemplate;
import mo.bitcode.kyrie.common.exception.KyrieException;
import mo.bitcode.kyrie.common.exception.KyrieExceptionCode;
import mo.bitcode.kyrie.common.util.CommonUtil;
import mo.bitcode.kyrie.repo.mongo.TeamMongoDbRepository;
import mo.bitcode.kyrie.repo.mongo.entity.TeamEntity;
import mo.bitcode.kyrie.service.application_config.ApplicationConfigService;
import mo.bitcode.kyrie.service.application_config.model.ApplicationConfig;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.team.model.TeamStatus;
import mo.bitcode.kyrie.service.team.model.TeamType;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class TeamServiceTemplate extends KyrieMongoAbstractServiceTemplate<TeamEntity> implements TeamService {

  private ApplicationConfigService applicationConfigService;
  private TeamMongoDbRepository teamMongoDbRepository;

  public TeamServiceTemplate(ApplicationConfigService applicationConfigService,
                             TeamMongoDbRepository teamMongoDbRepository) {
    super(teamMongoDbRepository);
    this.applicationConfigService = applicationConfigService;
    this.teamMongoDbRepository = teamMongoDbRepository;
  }

  @Override
  public TeamDto create(ObjectId captainId, TeamType teamType, String name, List<ObjectId> teamMemberIds) {
    final ApplicationConfig applicationConfig = this.applicationConfigService.getApplicationConfig();
    final TeamEntity teamEntity = TeamEntity.builder()
      .captainId(captainId)
      .name(name)
      .rating(applicationConfig.getTeamConfig().getCreateTeamDefaultRating())
      .season(applicationConfig.getSeason())
      .status(TeamStatus.ACTIVE)
      .teamMemberIds(teamMemberIds)
      .teamType(teamType)
      .build();
    return this.getTransformer().transform(this.create(teamEntity));
  }

  @Override
  public List<TeamDto> getUserTeams(String userId) {
    return this.getTransformer().transform(this.teamMongoDbRepository.findAllByTeamMemberIdsIn(CommonUtil.toObjectId(List.of(userId))));
  }

  @Override
  public TeamDto getDto(String teamId) {
    return this.getDto(new ObjectId(teamId));
  }

  @Override
  public TeamDto getDto(ObjectId teamId) {
    final TeamEntity teamEntity = this.teamMongoDbRepository.findById(teamId).orElse(null);
    if (teamEntity == null) {
      throw new KyrieException(KyrieExceptionCode.TEAM_NOT_EXISTED);
    }
    return this.getTransformer().transform(teamEntity);
  }

  @Override
  public List<TeamDto> getTeams(List<String> teamIds) {
    return this.getTeamsByRawIds(CommonUtil.toObjectId(teamIds));
  }

  @Override
  public List<TeamDto> getTeamsByRawIds(List<ObjectId> teamIds) {
    final List<TeamEntity> entities = this.teamMongoDbRepository.findAllByIdIn(teamIds);
    return this.getTransformer().transform(entities);
  }

  @Override
  public List<TeamDto> getTeamsByUserIds(List<String> userIds) {
    return null;
  }

  @Override
  public void validateUserIsCaptain(ObjectId userId, TeamDto teamDto) {
    final boolean userIsCaptain = this.userIsCaptain(userId, teamDto);
    if (!userIsCaptain) {
      throw new KyrieException(HttpStatus.BAD_REQUEST, "400", "User Is Not Captain");
    }
  }

  @Override
  public void validateUserIsCaptain(ObjectId userId, String teamId) {

  }

  public boolean userIsCaptain(ObjectId userId, TeamEntity teamEntity) {
    return userId.equals(teamEntity.getCaptainId());
  }

  public boolean userIsCaptain(ObjectId userId, TeamDto teamDto) {
    return userId.toString().equals(teamDto.getCaptainId());
  }

  protected TeamTransformer getTransformer() {
    return new TeamTransformer();
  }

}
