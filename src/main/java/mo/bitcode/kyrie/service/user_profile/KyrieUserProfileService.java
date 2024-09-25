package mo.bitcode.kyrie.service.user_profile;

import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.chat.model.ChatConnectionInfo;
import mo.bitcode.sms.sms_userprofile.SmsUserProfileService;
import org.bson.types.ObjectId;

import java.util.List;

public interface KyrieUserProfileService extends SmsUserProfileService<ObjectId, UserRole, UserProfile> {

  ChatConnectionInfo getChatConnectionInfo();
  List<UserProfile> getUserProfileByIds(List<String> ids);
  UserProfile onUserRegister(UserProfile userProfile);

}
