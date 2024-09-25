package mo.bitcode.kyrie.service.game;

import mo.bitcode.kyrie.service.game.model.EnterGameResponse;
import mo.bitcode.kyrie.service.game.model.GameDto;
import mo.bitcode.kyrie.service.game.model.GameEndedDto;
import mo.bitcode.kyrie.service.game.model.GameType;
import mo.bitcode.kyrie.service.team.model.TeamDto;

import java.util.List;

public interface GameService {

  EnterGameResponse enterFindGame(GameType gameType, TeamDto teamDto, List<String> availableLocations);
  GameDto get(String gameId);
  GameDto onGameEnded(GameEndedDto gameEndedDto);
  void validateUserNotInOtherGameOrQueue(List<String> userIds);

}
