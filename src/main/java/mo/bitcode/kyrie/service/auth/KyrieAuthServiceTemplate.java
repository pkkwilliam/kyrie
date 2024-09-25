package mo.bitcode.kyrie.service.auth;

import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.repo.mongo.PoormanVerificationRepository;
import mo.bitcode.kyrie.repo.mongo.entity.PoormanVerificationEntity;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.sms_auth.SmsAuthServicePoormanVerificationTemplate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;

public class KyrieAuthServiceTemplate
  extends SmsAuthServicePoormanVerificationTemplate<ObjectId, ObjectId, UserRole, UserProfile, PoormanVerificationEntity>
  implements KyrieAuthService {

  private KyrieUserProfileService kyrieUserProfileService;
  @Autowired
  private TeamService teamService;

  public KyrieAuthServiceTemplate(JwtService<ObjectId, UserRole, UserProfile> jwtService,
                                  boolean mockSmsService,
                                  SmsProviderService smsProviderService,
                                  KyrieUserProfileService kyrieUserProfileService,
                                  PoormanVerificationRepository poormanVerificationRepository) {
    super(jwtService, mockSmsService, smsProviderService, kyrieUserProfileService, poormanVerificationRepository);
    this.kyrieUserProfileService = kyrieUserProfileService;
  }

  @Override
  protected void onUserProfileCreated(UserProfile userProfile) {
    this.kyrieUserProfileService.onUserRegister(userProfile);
  }

  @Override
  public PoormanVerificationEntity createNewVerificationInstance() {
    return new PoormanVerificationEntity();
  }

  @Override
  protected String generatePoormanVerificationMessage(String randomVerificationString) {
    return "Basketball App Verification Code: " + randomVerificationString;
  }

}
