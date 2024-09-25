package mo.bitcode.kyrie.controller;

import mo.bitcode.core.controller.user_profile.UserProfileRestController;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.core.service.user_profile.UserProfileService;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.chat.model.ChatConnectionInfo;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;
import mo.bitcode.kyrie.service.user_profile.impl.KyrieUserProfileServiceImpl;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KyrieUserProfileRestController extends UserProfileRestController<ObjectId, UserRole, UserProfile> {

  private KyrieUserProfileService kyrieUserProfileService;

  public KyrieUserProfileRestController(KyrieUserProfileService kyrieUserProfileService) {
    super(kyrieUserProfileService);
    this.kyrieUserProfileService = kyrieUserProfileService;
  }

  @GetMapping("/chat_connection_info")
  public ResponseEntity<ChatConnectionInfo> getChatConnectionInfo() {
    return ResponseEntity.ok(this.kyrieUserProfileService.getChatConnectionInfo());
  }

}
