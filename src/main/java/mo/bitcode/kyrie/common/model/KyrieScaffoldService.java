package mo.bitcode.kyrie.common.model;

import mo.bitcode.core.service.rest_template.scaffold.ScaffoldServiceTemplate;
import mo.bitcode.core.service.rest_template.scaffold.table_base.TableBaseStatus;
import mo.bitcode.kyrie.repo.mongo.KyrieScaffoldRepository;
import org.bson.types.ObjectId;

public abstract class KyrieScaffoldService<ChildStatus extends TableBaseStatus,
  Child extends KyrieScaffoldTableBase<ChildStatus>>
  extends ScaffoldServiceTemplate<ObjectId, ObjectId, ChildStatus, Child> {

  public KyrieScaffoldService(KyrieScaffoldRepository<ChildStatus, Child> repository) {
    super(repository);
  }

  @Override
  protected ObjectId transformUserPrincipleToUserId(String userPrinciple) {
    return new ObjectId(userPrinciple);
  }

}
