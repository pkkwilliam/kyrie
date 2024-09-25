package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.common.bedrock.rest_template.KyrieMongoAbstractServiceRepository;
import mo.bitcode.kyrie.repo.mongo.entity.GameEntity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends KyrieMongoAbstractServiceRepository<GameEntity> {

  default GameEntity get(String gameId) {
    return findById(new ObjectId(gameId)).orElse(null);
  };

}
