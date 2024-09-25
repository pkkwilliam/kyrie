package mo.bitcode.kyrie.common.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import mo.bitcode.core.service.rest_template.scaffold.table_base.ScaffoldTableBase;
import mo.bitcode.core.service.rest_template.scaffold.table_base.TableBaseStatus;
import mo.bitcode.mongo_db.service.rest_template_table_base.MongoTableBase;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class KyrieScaffoldTableBase<ChildStatus extends TableBaseStatus>
  implements MongoTableBase, ScaffoldTableBase<ObjectId, ChildStatus> {

  @Id
  private ObjectId id;
  private ObjectId createById;
  private LocalDateTime createTime;
  private ObjectId updateById;
  private LocalDateTime updateTime;
  private ChildStatus status;

}
