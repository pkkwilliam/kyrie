package mo.bitcode.kyrie.common.model;

import mo.bitcode.core.common.ApplicationComponent;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;

public class KyrieUserServiceComponent extends ApplicationComponent {

  private KyrieUserProfileService userProfileService;

  protected UserProfile getUserProfile() {
    return this.userProfileService.getCurrentUserProfile();
  }

  protected KyrieUserProfileService getUserProfileService() {
    return userProfileService;
  }

}
