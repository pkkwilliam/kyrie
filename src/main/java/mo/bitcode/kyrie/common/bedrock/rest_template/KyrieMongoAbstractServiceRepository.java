package mo.bitcode.kyrie.common.bedrock.rest_template;

import mo.bitcode.core.service.rest_template.raw.AbstractServiceRepository;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface KyrieMongoAbstractServiceRepository<T extends KyrieTableBase>
  extends AbstractServiceRepository<ObjectId, T> {

}
