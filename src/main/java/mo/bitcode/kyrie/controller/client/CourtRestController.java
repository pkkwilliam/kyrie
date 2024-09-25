package mo.bitcode.kyrie.controller.client;

import mo.bitcode.kyrie.service.court.CourtService;
import mo.bitcode.kyrie.service.court.model.CourtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/court/v1")
public class CourtRestController {

  @Autowired
  private CourtService courtService;

  @GetMapping
  public ResponseEntity<Page<CourtDto>> getCourts(@RequestParam int pageRequest) {
    return ResponseEntity.ok(courtService.getCourts(pageRequest));
  }

  @PostMapping
  public ResponseEntity<CourtDto> createCourt(@RequestBody CourtDto court) {
    return ResponseEntity.ok(courtService.createCourt(court, false));
  }

}
