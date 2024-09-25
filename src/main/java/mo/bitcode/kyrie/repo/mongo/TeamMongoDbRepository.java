package mo.bitcode.kyrie.repo.mongo;

import mo.bitcode.kyrie.common.bedrock.rest_template.KyrieMongoAbstractServiceRepository;
import mo.bitcode.kyrie.repo.mongo.entity.TeamEntity;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamMongoDbRepository extends KyrieMongoAbstractServiceRepository<TeamEntity> {

  List<TeamEntity> findAllByIdIn(List<ObjectId> ids);
  List<TeamEntity> findAllByTeamMemberIdsIn(List<ObjectId> memberIds);

}
