package mo.bitcode.kyrie.repo.mongo.entity;

import lombok.Data;
import mo.bitcode.kyrie.service.cross_confirmation.model.CrossConfirmationBase;
import mo.bitcode.kyrie.service.cross_confirmation.model.TeamAssertScore;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "scoreCrossConfirmation")
@Data
public class ScoreCcEntity extends CrossConfirmationBase<ObjectId> {

  private TeamAssertScore awayTeamAssert;
  private TeamAssertScore homeTeamAssert;
  private int disagreeCount;

}
