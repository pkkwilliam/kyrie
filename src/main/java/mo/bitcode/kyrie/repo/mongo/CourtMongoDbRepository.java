package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.repo.mongo.entity.CourtEntity;
import mo.bitcode.kyrie.service.court.model.CourtStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtMongoDbRepository extends KyrieScaffoldRepository<CourtStatus, CourtEntity> {
}
