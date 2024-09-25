package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository
  extends mo.bitcode.core.service.user_profile.repo.UserProfileRepository<ObjectId, UserRole, UserProfile> {

  List<UserProfile> findAllByIdIn(List<String> ids);

}
