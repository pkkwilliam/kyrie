package mo.bitcode.kyrie.controller.client;

import mo.bitcode.kafka.producer.KafkaProducerService;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import mo.bitcode.kyrie.service.client_init.ClientInitService;
import mo.bitcode.kyrie.service.client_init.model.ClientInitInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/misc/v1")
public class ClientMiscRestController {

  @Autowired
  private ClientInitService clientInitService;

  @Qualifier(value = "TeamDtoKafka")
  @Autowired
  private KafkaProducerService<TeamDto> kafkaProducerService;

  @GetMapping("/init_info")
  public ResponseEntity<ClientInitInfo> getClientInitInfo() {
    return ResponseEntity.ok(this.clientInitService.getClientInitInfo());
  }

  @GetMapping("/test_kafak")
  public void t() {
    final TeamDto me = new TeamDto();
    me.setRating(592);
    this.kafkaProducerService.sendMessage(me);
  }

}
