package mo.bitcode.kyrie.service.auth;

import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.sms.sms_auth.SmsAuthService;
import org.bson.types.ObjectId;

public interface KyrieAuthService extends SmsAuthService<ObjectId, UserRole, UserProfile> {
  
}
