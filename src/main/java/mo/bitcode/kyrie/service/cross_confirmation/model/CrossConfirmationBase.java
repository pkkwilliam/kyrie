package mo.bitcode.kyrie.service.cross_confirmation.model;

import lombok.Data;

@Data
public class CrossConfirmationBase<IdType> {

  private IdType awayTeamId;
  private boolean awayTeamPreConfirm;
  private boolean awayTeamFinalConfirm;
  private IdType gameId;
  private IdType homeTeamId;
  private boolean homeTeamPreConfirm;
  private boolean homeTeamFinalConfirm;

}
