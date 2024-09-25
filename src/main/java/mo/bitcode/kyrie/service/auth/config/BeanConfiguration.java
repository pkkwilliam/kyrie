package mo.bitcode.kyrie.service.auth.config;

import mo.bitcode.core.security.AppSecurityConfig;
import mo.bitcode.core.security.impl.AppSecurityConfigImpl;
import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.core.security.jwt.impl.JwtServiceImplBasic;
import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileServiceTemplate;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.sms.provider.SmsProviderService;
import mo.bitcode.sms.provider.twilio.TwilioSmsProviderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public JwtService<ObjectId, UserRole, UserProfile> getJwtService(@Value("${jwt.token.expiration}") long expiration,
                                                                   @Value("${jwt.token.secret}") String secret) {
    return new JwtServiceImplBasic<>(expiration, secret);
  }

  @Bean
  public AppSecurityConfig getAppSecurityConfig(JwtService<ObjectId, UserRole, UserProfile> jwtService, KyrieUserProfileServiceTemplate kyrieUserProfileServiceTemplate) {
    return new AppSecurityConfigImpl(jwtService, kyrieUserProfileServiceTemplate);
  }

  @Bean("twilioSmsProvider")
  public SmsProviderService getTencentSmsProvider(@Value("${twilio.account.sid}") String accountSid,
                                                  @Value("${twilio.auth.token}") String token,
                                                  @Value("${twilio.sms.fromNumber}") String fromNumber,
                                                  @Value("${twilio.verification.sid}") String verificationSid) {
    return new TwilioSmsProviderService(accountSid, token, fromNumber, verificationSid);
  }

}
