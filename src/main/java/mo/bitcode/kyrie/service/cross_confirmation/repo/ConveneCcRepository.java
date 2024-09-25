package mo.bitcode.kyrie.service.cross_confirmation.repo;

import mo.bitcode.kyrie.common.repo.BasicRepo;
import mo.bitcode.kyrie.repo.mongo.entity.ConveneCcEntity;
import org.bson.types.ObjectId;

public interface ConveneCcRepository extends BasicRepo<ConveneCcEntity, ObjectId> {

  ConveneCcEntity getByGameId(ObjectId gameId);

}
