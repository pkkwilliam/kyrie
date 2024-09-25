package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.core.service.rest_template.scaffold.ScaffoldRepository;
import mo.bitcode.core.service.rest_template.scaffold.table_base.TableBaseStatus;
import mo.bitcode.kyrie.common.model.KyrieScaffoldTableBase;
import org.bson.types.ObjectId;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface KyrieScaffoldRepository<ChildStatus extends TableBaseStatus, Child extends KyrieScaffoldTableBase<ChildStatus>> extends ScaffoldRepository<ObjectId, ObjectId, ChildStatus, Child> {
}
