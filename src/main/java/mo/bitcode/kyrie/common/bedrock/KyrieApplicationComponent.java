package mo.bitcode.kyrie.common.bedrock;

import mo.bitcode.core.common.ApplicationComponent;
import org.bson.types.ObjectId;

public class KyrieApplicationComponent extends ApplicationComponent {

  public ObjectId getKyrieUserPrinciple() {
    return new ObjectId(this.getUserPrinciple());
  }

}
