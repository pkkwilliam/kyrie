package mo.bitcode.kyrie.service.live_chat.impl;

import mo.bitcode.kyrie.common.get_stream.GetStreamService;
import mo.bitcode.kyrie.common.model.KyrieUserServiceComponent;
import mo.bitcode.kyrie.service.live_chat.LiveChatService;
import mo.bitcode.kyrie.service.live_chat.model.LiveChatProvider;
import mo.bitcode.kyrie.service.live_chat.model.LiveChatConnectionInfo;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import org.springframework.stereotype.Service;

@Service
public class LiveChatServiceImpl extends KyrieUserServiceComponent implements LiveChatService {

  private GetStreamService getStreamService;

  public LiveChatServiceImpl(GetStreamService getStreamService) {
    this.getStreamService = getStreamService;
  }

  @Override
  public LiveChatConnectionInfo getLiveChatConnectionInfo() {
    final UserProfile requestUser = this.getUserProfile();
    final String token = this.getStreamService.getUserClientToken(requestUser);
    final LiveChatConnectionInfo result = new LiveChatConnectionInfo();
    result.setProvider(LiveChatProvider.GET_STREAM);
    result.setToken(token);
    return result;
  }

}
