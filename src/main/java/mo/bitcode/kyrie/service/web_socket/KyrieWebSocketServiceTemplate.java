package mo.bitcode.kyrie.service.web_socket;

import mo.bitcode.kyrie.service.game.model.GameStatus;
import mo.bitcode.kyrie.service.web_socket.model.SystemMessage;
import mo.bitcode.kyrie.service.web_socket.model.SystemMessageAction;
import mo.bitcode.kyrie.service.web_socket.model.SystemMessageType;

import java.util.List;

public abstract class KyrieWebSocketServiceTemplate implements KyrieWebSocketService {

  protected abstract void startWebSocket();

  @Override
  public void onApplicationStarted() {
    this.startWebSocket();
  }

  @Override
  public void sendAlertMessages(List<String> toUserIds, GameStatus gameStatus) {
    final SystemMessage systemMessage = SystemMessage.builder()
      .action(SystemMessageAction.GET_GAME)
      .message("Game Status Updated: " + gameStatus)
      .type(SystemMessageType.REFRESH)
      .build();
    this.sendPrivateSystemMessages(toUserIds, systemMessage);
  }

}
