package mo.bitcode.kyrie.service.court;

import mo.bitcode.kyrie.common.util.CommonUtil;
import mo.bitcode.kyrie.repo.mongo.entity.CourtEntity;
import mo.bitcode.kyrie.service.court.model.CourtDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CourtTransformer {

  public Page<CourtDto> transform(Page<CourtEntity> entities) {
    final List<CourtDto> courtDtos = entities.get().map(this::transform).collect(Collectors.toList());
    return CommonUtil.tranformPage(courtDtos, entities);
  }

  public CourtEntity transform(CourtDto courtDto) {
    final CourtEntity result = new CourtEntity();
    result.setAddress(courtDto.getAddress());
    result.setName(courtDto.getName());
    result.setStatus(courtDto.getCourtStatus());
    result.setNumberOfCourt(courtDto.getNumberOfCourt());
    return result;
  }

  public CourtDto transform(CourtEntity courtEntity) {
    if (courtEntity == null) {
      return null;
    }
    return CourtDto.builder()
      .id(courtEntity.getId().toString())
      .address(courtEntity.getAddress())
      .courtStatus(courtEntity.getStatus())
      .distance(0) // TODO need a way to calculate distance
      .name(courtEntity.getName())
      .numberOfCourt(courtEntity.getNumberOfCourt())
      .build();
  }

}
