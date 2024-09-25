package mo.bitcode.kyrie.common.model;

import mo.bitcode.kyrie.repo.mongo.entity.UserProfile;

public interface ReviewAttribute {

  String getComment();
  int getRating();
  UserProfile getUser();



}
