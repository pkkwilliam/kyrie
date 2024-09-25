package mo.bitcode.kyrie.service.client_init.model;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.core.web_socket.model.WebSocketClientConnectInfo;

@Builder
@Data
public class ClientInitInfo {

  private WebSocketClientConnectInfo webSocketClientConnectInfo;

}
