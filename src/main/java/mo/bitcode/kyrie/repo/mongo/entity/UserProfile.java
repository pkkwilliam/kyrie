package mo.bitcode.kyrie.repo.mongo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;
import mo.bitcode.core.security.model.UserRole;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "userProfile")
public class UserProfile extends mo.bitcode.core.service.user_profile.model.UserProfile<ObjectId, UserRole> {

  @Id
  @JsonIgnore
  private ObjectId id;

  public String getSid() {
    return this.id.toString();
  }

}
