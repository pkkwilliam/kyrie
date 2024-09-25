package mo.bitcode.kyrie.service.client_init.impl;

import mo.bitcode.kyrie.service.client_init.ClientInitServiceTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientInitServiceImpl extends ClientInitServiceTemplate {

  public ClientInitServiceImpl(@Value("${web.socket.client.listen.event}") List<String> webSocketClientListenEvent,
                               @Value("${web.socket.host}") String webSocketHost,
                               @Value("${web.socket.port}") int webSocketPort) {
    super(webSocketClientListenEvent, webSocketHost, webSocketPort);
  }

}
