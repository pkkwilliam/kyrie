package mo.bitcode.kyrie.controller;

import mo.bitcode.kyrie.service.live_chat.LiveChatService;
import mo.bitcode.kyrie.service.live_chat.model.LiveChatConnectionInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/live_chat/v1")
public class KyrieUserLiveChatRestController {

  private LiveChatService liveChatService;

  public KyrieUserLiveChatRestController(LiveChatService liveChatService) {
    this.liveChatService = liveChatService;
  }

  @GetMapping("/live_chat_connection_info")
  public ResponseEntity<LiveChatConnectionInfo> getLiveChatConnectionInfo() {
    return ResponseEntity.ok(this.liveChatService.getLiveChatConnectionInfo());
  }

}
