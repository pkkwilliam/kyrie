package mo.bitcode.kyrie.common.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import mo.bitcode.mongo_db.service.rest_template_table_base.MongoTableBase;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class KyrieTableBase implements MongoTableBase {

  @Id
  private ObjectId id;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;

}
