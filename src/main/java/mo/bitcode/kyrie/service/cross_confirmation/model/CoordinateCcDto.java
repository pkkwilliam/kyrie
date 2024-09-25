package mo.bitcode.kyrie.service.cross_confirmation.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoordinateCcDto extends CrossConfirmationBaseDto {

  private String proposeCourtId;
  private LocalDateTime proposeDateTime;

}
