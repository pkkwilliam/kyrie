package mo.bitcode.kyrie.service.web_socket;

import mo.bitcode.kyrie.service.application_init.ApplicationComponentService;
import mo.bitcode.kyrie.service.game.model.GameStatus;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.web_socket.model.SystemMessage;

import java.util.List;

public interface KyrieWebSocketService extends ApplicationComponentService {

  void sendAlertMessages(List<String> toUserIds, GameStatus gameStatus);
  void sendPrivateSystemMessage(String userSid, SystemMessage systemMessage);
  void sendPrivateSystemMessages(List<String> userIds, SystemMessage systemMessage);
  void sendPublicSystemMessage(SystemMessage systemMessage);

}
