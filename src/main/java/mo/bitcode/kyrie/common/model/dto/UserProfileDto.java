package mo.bitcode.kyrie.common.model.dto;

import lombok.Data;
import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;

@Data
public class UserProfileDto {

  private String id;
  private int creditScore;
  private String imageUrl;
  private String username;

  public UserProfileDto(UserProfile userProfile) {
    this.id = userProfile.getId().toString();
    this.imageUrl = userProfile.getImageUrl();
    this.username = userProfile.getUsername();
  }

}
