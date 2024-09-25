package mo.bitcode.kyrie.service.cross_confirmation.repo;

import mo.bitcode.kyrie.common.repo.BasicRepo;
import mo.bitcode.kyrie.repo.mongo.entity.ScoreCcEntity;
import org.bson.types.ObjectId;

public interface ScoreCcRepository extends BasicRepo<ScoreCcEntity, ObjectId> {

  ScoreCcEntity getByGameId(ObjectId gameId);

}
