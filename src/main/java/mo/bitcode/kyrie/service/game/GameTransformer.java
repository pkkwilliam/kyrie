package mo.bitcode.kyrie.service.game;

import mo.bitcode.kyrie.common.model.TeamSide;
import mo.bitcode.kyrie.common.util.CommonUtil;
import mo.bitcode.kyrie.repo.mongo.entity.GameEntity;
import mo.bitcode.kyrie.service.game.model.GameDto;
import mo.bitcode.kyrie.service.team.model.TeamDto;

import java.util.List;
import java.util.Map;

public class GameTransformer {

  public GameDto transform(GameEntity gameEntity, List<TeamDto> teamDtos) {
    final Map<TeamSide, TeamDto> teamSideDto =
      CommonUtil.determineTeamSide(gameEntity.getHomeTeamId().toString(), teamDtos);
    final GameDto result = new GameDto();
    result.setId(gameEntity.getId().toString());
    result.setAwayTeamScore(gameEntity.getAwayTeamScore());
    result.setHomeTeamScore(gameEntity.getHomeTeamScore());
    result.setGameType(gameEntity.getGameType());
    result.setGameStatus(gameEntity.getGameStatus());
    result.setScoreCalculateType(gameEntity.getScoreCalculateType());
    result.setTeamType(gameEntity.getTeamType());
    result.setAwayTeamDto(teamSideDto.get(TeamSide.AWAY));
    result.setHomeTeamDto(teamSideDto.get(TeamSide.HOME));
    return result;
  }

}
