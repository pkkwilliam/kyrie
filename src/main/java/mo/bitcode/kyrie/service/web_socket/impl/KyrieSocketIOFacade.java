package mo.bitcode.kyrie.service.web_socket.impl;

import mo.bitcode.kyrie.common.socket_io.KyrieSocketIOServiceImpl;
import mo.bitcode.kyrie.service.web_socket.KyrieWebSocketServiceTemplate;
import mo.bitcode.kyrie.service.web_socket.model.SystemMessage;
import mo.bitcode.kyrie.service.web_socket.model.SystemMessageAction;
import mo.bitcode.kyrie.service.web_socket.model.SystemMessageType;
import mo.bitcode.kyrie.service.web_socket.model.WebSocketTopic;
import org.bson.types.ObjectId;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KyrieSocketIOFacade extends KyrieWebSocketServiceTemplate {

  private KyrieSocketIOServiceImpl socketIOService;

  public KyrieSocketIOFacade(KyrieSocketIOServiceImpl socketIOService) {
    this.socketIOService = socketIOService;
  }

  @Override
  protected void startWebSocket() {
    this.socketIOService.start();
  }

  @Override
  public void onApplicationPreDestroy() {
    this.socketIOService.stop();
  }


  @Override
  public void sendPrivateSystemMessage(String userSid, SystemMessage systemMessage) {
    this.socketIOService.sendPrivateMessage(WebSocketTopic.PRIVATE_SYSTEM.toString(), userSid, systemMessage);
  }

  @Override
  public void sendPrivateSystemMessages(List<String> userIds, SystemMessage systemMessage) {
    for (String userId: userIds) {
      this.sendPrivateSystemMessage(userId, systemMessage);
    }
  }

  @Override
  public void sendPublicSystemMessage(SystemMessage systemMessage) {
    this.socketIOService.sendPublicMessage(WebSocketTopic.SYSTEM.toString(), systemMessage);
  }


  @Scheduled(fixedRate = 2000)
  public void sendMessage() {
    final SystemMessage systemMessage = SystemMessage.builder()
      .action(SystemMessageAction.GET_TEAM_NOTIFICATION)
      .message("ME TESTTTT")
      .type(SystemMessageType.REFRESH)
      .build();
    this.sendPublicSystemMessage(systemMessage);
  }

}
