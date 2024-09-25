package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.repo.mongo.entity.CoordinateCcEntity;
import mo.bitcode.kyrie.service.cross_confirmation.repo.CoordinateCcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateCcMongoDbRepository extends CoordinateCcRepository, MongoDbBasicCrudRepository<CoordinateCcEntity> {

}
