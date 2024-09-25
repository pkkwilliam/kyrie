package mo.bitcode.kyrie.service.auth.impl;

import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.repo.mongo.PoormanVerificationRepository;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.auth.KyrieAuthServiceTemplate;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileService;
import mo.bitcode.sms.provider.SmsProviderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KyrieAuthServiceImpl extends KyrieAuthServiceTemplate {

  public KyrieAuthServiceImpl(JwtService<ObjectId, UserRole, UserProfile> jwtService,
                              @Value("${mock.sms.message}") boolean mockSmsService,
                              SmsProviderService smsProviderService,
                              KyrieUserProfileService kyrieUserProfileService,
                              PoormanVerificationRepository poormanVerificationRepository) {
    super(jwtService, mockSmsService, smsProviderService, kyrieUserProfileService, poormanVerificationRepository);
  }

}
