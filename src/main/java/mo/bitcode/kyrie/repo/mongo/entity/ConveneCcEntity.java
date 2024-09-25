package mo.bitcode.kyrie.repo.mongo.entity;

import mo.bitcode.kyrie.service.cross_confirmation.model.CrossConfirmationBase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "conveneCrossConfirmation")
public class ConveneCcEntity extends CrossConfirmationBase<ObjectId> {
}
