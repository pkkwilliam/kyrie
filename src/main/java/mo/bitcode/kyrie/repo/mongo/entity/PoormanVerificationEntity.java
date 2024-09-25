package mo.bitcode.kyrie.repo.mongo.entity;

import mo.bitcode.mongo_db.service.poorman_verification.model.PoormanVerificationMongoDbEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "poormanVerification")
public class PoormanVerificationEntity extends PoormanVerificationMongoDbEntity {
}
