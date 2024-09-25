package mo.bitcode.kyrie.service.game.model;

import lombok.Data;

import java.util.List;

@Data
public class EnterGameRequest {

  private List<String> allowLocationIds;

}
