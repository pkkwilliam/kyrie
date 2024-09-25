package mo.bitcode.kyrie.common.bedrock.rest_template;

import mo.bitcode.core.service.rest_template.raw.AbstractServiceRepository;
import mo.bitcode.core.service.rest_template.raw.AbstractServiceTemplate;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import org.bson.types.ObjectId;

public abstract class KyrieMongoAbstractServiceTemplate<T extends KyrieTableBase> extends AbstractServiceTemplate<ObjectId, T> {

  public KyrieMongoAbstractServiceTemplate(AbstractServiceRepository<ObjectId, T> abstractServiceRepository) {
    super(abstractServiceRepository);
  }

}
