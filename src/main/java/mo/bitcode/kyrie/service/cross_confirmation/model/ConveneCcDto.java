package mo.bitcode.kyrie.service.cross_confirmation.model;

import lombok.Data;

@Data
public class ConveneCcDto extends CrossConfirmationBaseDto {

  private TeamAssertScore awayTeamAssert;
  private TeamAssertScore homeTeamAssert;

}
