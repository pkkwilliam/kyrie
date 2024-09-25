package mo.bitcode.kyrie.common.get_stream;

import io.getstream.chat.java.models.User;
import io.getstream.chat.java.services.framework.DefaultClient;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

@Service
public class GetStreamService {

  private String getStreamApiKey;
  private String getStreamApiSecret;

  public GetStreamService() {
    this.getStreamApiKey = "zxk7u6hbqr6q";
    this.getStreamApiSecret = "2crnmzquqcjpahv2d988su7ehjbcnsxvae7fqpfp2cm6qk8xakyqjheupu2ac2vu";
  }

  public String getUserClientToken(UserProfile userProfile) {
    final Calendar calendar = new GregorianCalendar();
    calendar.add(Calendar.MINUTE, 60);
    final String token = User.createToken(userProfile.getId().toString(), calendar.getTime(), null);
    return token;
  }

  private void initClient() {
    final Properties properties = new Properties();
    properties.put(DefaultClient.API_KEY_PROP_NAME, this.getStreamApiKey);
    properties.put(DefaultClient.API_SECRET_PROP_NAME, this.getStreamApiSecret);
    final DefaultClient defaultClient = new DefaultClient(properties);
    DefaultClient.setInstance(defaultClient);
  }

}
