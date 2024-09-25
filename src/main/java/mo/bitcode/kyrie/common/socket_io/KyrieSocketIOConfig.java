package mo.bitcode.kyrie.common.socket_io;

import mo.bitcode.socket_io.config.SocketIOConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KyrieSocketIOConfig extends SocketIOConfig {

  public KyrieSocketIOConfig(@Value("${web.socket.host}") String webSocketHost,
                             @Value("${web.socket.port}") int webSocketPort) {
    super(webSocketHost, webSocketPort, true);
  }

}
