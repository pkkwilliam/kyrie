package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.repo.mongo.entity.ConveneCcEntity;
import mo.bitcode.kyrie.service.cross_confirmation.repo.ConveneCcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConveneCcMongoDbRepository extends ConveneCcRepository, MongoDbBasicCrudRepository<ConveneCcEntity> {

}
