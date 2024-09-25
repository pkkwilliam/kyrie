package mo.bitcode.kyrie.repo.mongo.entity;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import mo.bitcode.kyrie.service.team.model.TeamAttribute;
import mo.bitcode.kyrie.service.team.model.TeamStatus;
import mo.bitcode.kyrie.service.team.model.TeamType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(value = "team")
public class TeamEntity extends KyrieTableBase implements TeamAttribute {

  private ObjectId captainId;
  private String name;
  private int rating;
  private int season;
  private TeamStatus status;
  private TeamType teamType;
  private List<ObjectId> teamMemberIds;

}
