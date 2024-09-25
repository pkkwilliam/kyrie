package mo.bitcode.kyrie.service.user_profile;

import mo.bitcode.core.security.model.UserRole;
import mo.bitcode.kyrie.repo.mongo.UserProfileRepository;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;
import mo.bitcode.kyrie.service.chat.ChatService;
import mo.bitcode.kyrie.service.chat.model.ChatConnectionInfo;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.team.model.TeamType;
import mo.bitcode.sms.sms_userprofile.SmsUserProfileServiceTemplate;
import org.bson.types.ObjectId;

import java.util.List;

public abstract class KyrieUserProfileServiceTemplate
        extends SmsUserProfileServiceTemplate<ObjectId, UserRole, UserProfile>
        implements KyrieUserProfileService {

  private ChatService chatService;
  private TeamService teamService;
  private UserProfileRepository userProfileRepository;

  public KyrieUserProfileServiceTemplate(ChatService chatService, TeamService teamService, UserProfileRepository userProfileRepository) {
    super(userProfileRepository);
    this.chatService = chatService;
    this.teamService = teamService;
    this.userProfileRepository = userProfileRepository;
  }

  @Override
  protected UserProfile generateEmptyApplicationProfileObject() {
    return new UserProfile();
  }

  @Override
  protected UserRole getBasicUserRole() {
    return UserRole.ROLE_USER;
  }

  @Override
  public ChatConnectionInfo getChatConnectionInfo() {
    return this.chatService.getConnectionInfo(this.getUserPrinciple());
  }

  @Override
  protected String getSidPrefix() {
    return "K11";
  }

  @Override
  public List<UserProfile> getUserProfileByIds(List<String> ids) {
    return this.userProfileRepository.findAllByIdIn(ids);
  }

  @Override
  public UserProfile onUserRegister(UserProfile userProfile) {
    final ObjectId userProfileId = userProfile.getId();
    this.teamService.create(userProfileId, TeamType.ONE_VS_ONE, userProfile.getName(), List.of(userProfileId));
    this.chatService.createChatAccount(userProfile);
    return userProfile;
  }

}
