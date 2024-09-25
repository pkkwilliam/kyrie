package mo.bitcode.kyrie.service.credit.model;

import lombok.Data;
import mo.bitcode.kyrie.service.game.model.GameType;

import java.util.Set;

@Data
public class CreditResponse {

  private Set<GameType> allowToJoinGameType;

}
