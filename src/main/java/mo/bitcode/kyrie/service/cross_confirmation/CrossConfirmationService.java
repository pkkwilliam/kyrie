package mo.bitcode.kyrie.service.cross_confirmation;

import mo.bitcode.kyrie.service.cross_confirmation.model.ConveneCcDto;
import mo.bitcode.kyrie.service.cross_confirmation.model.CoordinateCcDto;
import mo.bitcode.kyrie.service.cross_confirmation.model.ScoreCcDto;

public interface CrossConfirmationService {

  /**
   * Step 1. Coordinate - where both parties discuss where and when they should meet
   * Step 2. Convene - where both parties confirm that they arrived.
   */

  ConveneCcDto initConveneCc(String gameId, String homeTeamId, String awayTeamId);
  CoordinateCcDto initCoordinateCc(String gameId, String homeTeamId, String awayTeamId);
  ScoreCcDto initScoreCc(String gameId, String homeTeamId, String awayTeamId);
  ConveneCcDto updateConveneCc(String gameId, String teamId, ConveneCcDto dto);
  CoordinateCcDto updateCoordinateCc(String gameId, String teamId, CoordinateCcDto dto);
  ScoreCcDto updateScoreCc(String gameId, String teamId, ScoreCcDto dto);


}
