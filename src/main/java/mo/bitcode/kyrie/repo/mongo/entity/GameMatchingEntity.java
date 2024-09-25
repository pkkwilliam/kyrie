package mo.bitcode.kyrie.repo.mongo.entity;

import lombok.Builder;
import lombok.Data;
import mo.bitcode.kyrie.common.model.KyrieTableBase;
import mo.bitcode.kyrie.service.game.model.GameType;
import mo.bitcode.kyrie.service.team.model.TeamType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(value = "gameMatching")
public class GameMatchingEntity extends KyrieTableBase {

  private List<String> availableLocationIds;
  private GameType gameType;
  private ObjectId teamId;
  private TeamType teamType;

}
