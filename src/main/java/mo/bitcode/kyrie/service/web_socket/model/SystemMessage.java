package mo.bitcode.kyrie.service.web_socket.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SystemMessage {

  private SystemMessageAction action;
  private String message;
  private SystemMessageType type;

}
