package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.repo.mongo.entity.ScoreCcEntity;
import mo.bitcode.kyrie.service.cross_confirmation.repo.ScoreCcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreCcMongoDbRepository extends ScoreCcRepository, MongoDbBasicCrudRepository<ScoreCcEntity> {

}
