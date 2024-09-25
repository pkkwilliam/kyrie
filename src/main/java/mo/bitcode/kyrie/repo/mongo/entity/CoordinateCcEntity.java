package mo.bitcode.kyrie.repo.mongo.entity;

import lombok.Data;
import mo.bitcode.kyrie.service.cross_confirmation.model.CrossConfirmationBase;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "coordinateCrossConfirmation")
@Data
public class CoordinateCcEntity extends CrossConfirmationBase<ObjectId> {

  private ObjectId proposeCourtId;
  private LocalDateTime proposeDateTime;

}
