package mo.bitcode.kyrie.service.user_profile.impl;

import mo.bitcode.kyrie.repo.mongo.UserProfileRepository;
import mo.bitcode.kyrie.service.chat.ChatService;
import mo.bitcode.kyrie.service.team.TeamService;
import mo.bitcode.kyrie.service.user_profile.KyrieUserProfileServiceTemplate;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class KyrieUserProfileServiceImpl extends KyrieUserProfileServiceTemplate {

  public KyrieUserProfileServiceImpl(ChatService chatService, TeamService teamService, UserProfileRepository kyrieUserProfileRepository) {
    super(chatService, teamService, kyrieUserProfileRepository);
  }

  @Override
  protected ObjectId generateSid() {
    return null;
  }

  @Override
  protected ObjectId toSid(String sid) {
    return new ObjectId(sid);
  }

}
