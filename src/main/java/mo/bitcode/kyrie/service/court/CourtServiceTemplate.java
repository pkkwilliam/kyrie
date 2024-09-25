package mo.bitcode.kyrie.service.court;

import mo.bitcode.kyrie.common.model.KyrieScaffoldService;
import mo.bitcode.kyrie.repo.mongo.CourtMongoDbRepository;
import mo.bitcode.kyrie.repo.mongo.entity.CourtEntity;
import mo.bitcode.kyrie.service.court.model.CourtDto;
import mo.bitcode.kyrie.service.court.model.CourtStatus;
import org.springframework.data.domain.Page;

public abstract class CourtServiceTemplate extends KyrieScaffoldService<CourtStatus, CourtEntity> implements CourtService {

  private CourtMongoDbRepository repository;

  public CourtServiceTemplate(CourtMongoDbRepository repository) {
    super(repository);
    this.repository = repository;
  }

  @Override
  public Page<CourtDto> getCourts(int pageRequest) {
    final Page<CourtEntity> entities = this.getPagination(pageRequest, 20);
    return this.getTransformer().transform(entities);
  }

  @Override
  public CourtDto createCourt(CourtDto dto, boolean fromManagement) {
    final CourtEntity toInsertEntity = this.getTransformer().transform(dto);
    final CourtEntity createdEntity = this.create(toInsertEntity);
    return this.getTransformer().transform(createdEntity);
  }

  protected CourtTransformer getTransformer() {
    return new CourtTransformer();
  }

  @Override
  protected CourtStatus getObjectCreatedStatus() {
    return CourtStatus.PENDING;
  }

}
