package mo.bitcode.kyrie.service.cross_confirmation.repo;

import mo.bitcode.kyrie.common.repo.BasicRepo;
import mo.bitcode.kyrie.repo.mongo.entity.CoordinateCcEntity;
import org.bson.types.ObjectId;

public interface CoordinateCcRepository extends BasicRepo<CoordinateCcEntity, ObjectId> {

  CoordinateCcEntity getByGameId(ObjectId gameId);

}
