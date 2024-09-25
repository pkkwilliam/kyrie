package mo.bitcode.kyrie.controller.client;

import mo.bitcode.kyrie.facade.GameFacade;
import mo.bitcode.kyrie.service.game.model.EnterGameRequest;
import mo.bitcode.kyrie.service.game.model.EnterGameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/game/v1")
public class ClientGameRestController {

  @Autowired
  private GameFacade gameFacade;

  @PostMapping("/{teamId}/find_game")
  public ResponseEntity<EnterGameResponse> findGame(@PathVariable String teamId, @RequestBody EnterGameRequest enterGameRequest) {
    final EnterGameResponse enterGameResponse = this.gameFacade.findGame(teamId, enterGameRequest);
    return ResponseEntity.ok(enterGameResponse);
  }

}
