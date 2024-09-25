package mo.bitcode.kyrie.service.credit;

import mo.bitcode.kyrie.service.game.model.GameDto;
import mo.bitcode.kyrie.service.game.model.GameType;

import java.util.List;

public interface CreditService {

  void processCreditOnGameEnd(GameDto gameDto);
  void validateUsersAllowToJoinGame(GameType gameType, List<String> userIds);

}
