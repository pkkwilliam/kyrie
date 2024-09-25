package mo.bitcode.kyrie.common.sendbird;

import mo.bitcode.sendbird.SendbirdFacade;
import mo.bitcode.sendbird.SendbirdRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendbirdConfiguration {

  private static final String APP_ID = "1E2BBFA9-279B-4696-8F87-8488F502036E";
  private static final String API_TOKEN = "bfc82853272ace23c48c659b77274eb6de4cb18b";

  @Bean
  public SendbirdFacade getSendbirdFacade() {
    return new SendbirdFacade(new SendbirdRestTemplate(APP_ID, API_TOKEN));
  }

}
