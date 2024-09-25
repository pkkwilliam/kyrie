package mo.bitcode.kyrie.service.client_init;

import mo.bitcode.core.web_socket.model.WebSocketClientConnectInfo;
import mo.bitcode.core.web_socket.model.WebSocketType;
import mo.bitcode.kyrie.service.client_init.model.ClientInitInfo;

import java.util.List;

public abstract class ClientInitServiceTemplate implements ClientInitService {

  private WebSocketClientConnectInfo webSocketClientConnectInfo;

  public ClientInitServiceTemplate(List<String> eventToListen,
                                   String webSocketHost,
                                   int webSocketPort) {
    webSocketClientConnectInfo = WebSocketClientConnectInfo.builder()
      .eventToListen(eventToListen)
      .url(webSocketHost + ":" + webSocketPort)
      .webSocketType(WebSocketType.SOCKET_IO_V2)
      .build();
  }

  @Override
  public ClientInitInfo getClientInitInfo() {
    return ClientInitInfo.builder()
      .webSocketClientConnectInfo(this.webSocketClientConnectInfo)
      .build();
  }

}
