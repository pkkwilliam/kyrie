package mo.bitcode.kyrie.common.bedrock.security;

import mo.bitcode.core.security.AppSecurityConfig;
import mo.bitcode.core.security.jwt.JwtService;
import mo.bitcode.core.service.user_profile.UserProfileServiceTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KyrieAppSecurityConfig extends AppSecurityConfig {
  public KyrieAppSecurityConfig(JwtService jwtService, UserProfileServiceTemplate userProfileServiceTemplate) {
    super(jwtService, userProfileServiceTemplate);
  }

}
