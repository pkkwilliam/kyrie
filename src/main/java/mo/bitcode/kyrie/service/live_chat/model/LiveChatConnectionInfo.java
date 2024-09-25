package mo.bitcode.kyrie.service.live_chat.model;

import lombok.Data;

@Data
public class LiveChatConnectionInfo {

  private LiveChatProvider provider;
  private String token;

}
