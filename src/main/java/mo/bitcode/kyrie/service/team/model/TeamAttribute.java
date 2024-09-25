package mo.bitcode.kyrie.service.team.model;

import org.bson.types.ObjectId;

public interface TeamAttribute {

  ObjectId getId();
  int getRating();
  int getSeason();
  TeamStatus getStatus();
  TeamType getTeamType();

}
