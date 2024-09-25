package mo.bitcode.kyrie.facade.handler;

import mo.bitcode.kyrie.service.cross_confirmation.model.CrossConfirmationBase;

@FunctionalInterface
public interface GameCrossConfirmationUpdateHandler<T extends CrossConfirmationBase<String>> {

  T update();

}
