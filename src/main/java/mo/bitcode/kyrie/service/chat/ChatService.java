package mo.bitcode.kyrie.service.chat;

import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.chat.model.ChatConnectionInfo;
import mo.bitcode.kyrie.service.chat.model.ChatGroupInfo;

import java.util.List;

public interface ChatService {

  ChatConnectionInfo createChatAccount(UserProfile userProfile);
  ChatGroupInfo createGroupChat(String groupName, List<String> userIds);
  ChatConnectionInfo getConnectionInfo(String userId);
  void sendAdminMessageToGroup(String groupId, String message);

}
