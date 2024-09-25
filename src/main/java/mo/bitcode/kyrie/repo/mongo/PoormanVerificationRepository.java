package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.repo.mongo.entity.PoormanVerificationEntity;
import mo.bitcode.mongo_db.service.poorman_verification.PoormanVerificationMongoDbRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoormanVerificationRepository extends PoormanVerificationMongoDbRepository<PoormanVerificationEntity> {
}
