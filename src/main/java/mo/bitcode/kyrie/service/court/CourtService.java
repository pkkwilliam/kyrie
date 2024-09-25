package mo.bitcode.kyrie.service.court;

import mo.bitcode.kyrie.service.court.model.CourtDto;
import org.springframework.data.domain.Page;

public interface CourtService {

  Page<CourtDto> getCourts(int pageRequest);
  CourtDto createCourt(CourtDto dto, boolean fromManagement);

}
