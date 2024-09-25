package mo.bitcode.kyrie.facade;

import mo.bitcode.kyrie.service.cross_confirmation.model.ConveneCcDto;
import mo.bitcode.kyrie.service.cross_confirmation.model.CoordinateCcDto;
import mo.bitcode.kyrie.service.cross_confirmation.model.ScoreCcDto;
import mo.bitcode.kyrie.service.game.model.EnterGameRequest;
import mo.bitcode.kyrie.service.game.model.EnterGameResponse;

public interface GameFacade {

  EnterGameResponse findGame(String teamId, EnterGameRequest enterGameRequest);
  CoordinateCcDto updateGameCoordinate(String gameId, String teamId, CoordinateCcDto dto);
  ConveneCcDto updateGameConvene(String gameId, String teamId, ConveneCcDto dto);
  ScoreCcDto updateScore(String gameId, String teamId, ScoreCcDto dto);

}
