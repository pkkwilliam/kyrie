package mo.bitcode.kyrie.service.credit.impl;

import mo.bitcode.kyrie.service.credit.CreditService;
import mo.bitcode.kyrie.service.credit.model.CreditResponse;
import mo.bitcode.kyrie.service.game.model.GameDto;
import mo.bitcode.kyrie.service.game.model.GameType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CreditServiceImpl implements CreditService {

  @Override
  public void processCreditOnGameEnd(GameDto gameDto) {

  }

  @Override
  public void validateUsersAllowToJoinGame(GameType gameType, List<String> userIds) {

  }

}
