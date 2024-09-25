package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.common.bedrock.rest_template.KyrieMongoAbstractServiceRepository;
import mo.bitcode.kyrie.repo.mongo.entity.GameMatchingEntity;
import mo.bitcode.kyrie.service.game.model.GameType;
import mo.bitcode.kyrie.service.team.model.TeamType;
import org.bson.types.ObjectId;

import java.util.List;

public interface GameMatchingRepository extends KyrieMongoAbstractServiceRepository<GameMatchingEntity> {

  List<GameMatchingEntity> findAllByGameTypeAndTeamTypeAndAndAvailableLocationIdsIn(GameType gameType, TeamType teamType, List<String> availableLocations);
  void deleteByTeamId(ObjectId toRemoveTeamId);

}
