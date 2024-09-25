package mo.bitcode.kyrie.service.cross_confirmation.model;

import lombok.Data;

@Data
public class CrossConfirmationBaseDto extends CrossConfirmationBase<String> {

  private CrossConfirmationResult result;

}
