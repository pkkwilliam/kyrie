package mo.bitcode.kyrie.service.chat.impl;

import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.chat.ChatService;
import mo.bitcode.kyrie.service.chat.model.ChatConnectionInfo;
import mo.bitcode.kyrie.service.chat.model.ChatGroupInfo;
import mo.bitcode.sendbird.SendbirdFacade;
import mo.bitcode.sendbird.model.ChannelType;
import mo.bitcode.sendbird.service.channel.model.create_group_channel.CreateGroupChannelRequest;
import mo.bitcode.sendbird.service.channel.model.create_group_channel.CreateGroupChannelResponse;
import mo.bitcode.sendbird.service.message.model.MessageType;
import mo.bitcode.sendbird.service.message.model.SendMessageRequest;
import mo.bitcode.sendbird.service.user.model.create_user.CreateUserRequest;
import mo.bitcode.sendbird.service.user.model.create_user.CreateUserResponse;
import mo.bitcode.sendbird.service.user.model.issue_session_token.IssueSessionTokenResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SendbirdChatService implements ChatService {

  private static final long DAY_IN_MILLIS = 24 * 60 * 60 * 1000L;
  private SendbirdFacade sendbirdFacade;

  public SendbirdChatService(SendbirdFacade sendbirdFacade) {
    this.sendbirdFacade = sendbirdFacade;
  }

  @Override
  public ChatConnectionInfo createChatAccount(UserProfile userProfile) {
    final String userId = userProfile.getId().toString();
    final CreateUserRequest createUserRequest = new CreateUserRequest();
    createUserRequest.setUser_id(userId);
    createUserRequest.setNickname(userId);
    createUserRequest.setProfile_url("https://sendbird.com/main/img/profiles/profile_05_512px.png");
    createUserRequest.setIssue_access_token(true);
    final CreateUserResponse response = this.sendbirdFacade.getUserService().createUser(createUserRequest);
    return this.getConnectionInfo(userId);
  }



  @Override
  public ChatGroupInfo createGroupChat(String groupName, List<String> userIds) {
    final String channelUrl = UUID.randomUUID().toString();
    final CreateGroupChannelRequest request = new CreateGroupChannelRequest();
    request.setName(groupName);
    request.setChannel_url(channelUrl);
    request.setUser_ids(userIds);
    request.setOperator_ids(new ArrayList<>());
    final CreateGroupChannelResponse response = this.sendbirdFacade.getChannelService().createGroupChannel(request);

    final ChatGroupInfo chatGroupInfo = new ChatGroupInfo();
    chatGroupInfo.setGroupId(response.getChannel_url());
    return chatGroupInfo;
  }

  @Override
  public ChatConnectionInfo getConnectionInfo(String userId) {
    final IssueSessionTokenResponse response =
      this.sendbirdFacade.getUserService().issueSessionToken(userId, this.getSessionTokenExpires());
    final ChatConnectionInfo chatConnectionInfo = new ChatConnectionInfo();
    chatConnectionInfo.setAccountId(userId);
    chatConnectionInfo.setToken(response.getToken());
    return chatConnectionInfo;
  }

  @Override
  public void sendAdminMessageToGroup(String groupId, String message) {
    final SendMessageRequest sendMessageRequest = new SendMessageRequest();
    sendMessageRequest.setMessage(message);
    this.sendbirdFacade
      .getMessageService()
      .sendMessage(ChannelType.GROUP_CHANNELS, MessageType.ADMIN, groupId, sendMessageRequest);
  }

  protected long getSessionTokenExpires() {
    // Three day from current
    return System.currentTimeMillis() + DAY_IN_MILLIS * 3;
  }

}
