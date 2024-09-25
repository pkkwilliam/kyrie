package mo.bitcode.kyrie.service.application_init;

import mo.bitcode.kyrie.service.web_socket.KyrieWebSocketService;
import org.springframework.stereotype.Service;

@Service
public class ApplicationInitServiceImpl implements ApplicationInitService {

  private KyrieWebSocketService kyrieWebSocketService;

  public ApplicationInitServiceImpl(KyrieWebSocketService kyrieWebSocketService) {
    this.kyrieWebSocketService = kyrieWebSocketService;
  }

  @Override
  public void startSocketIO() {
    this.kyrieWebSocketService.onApplicationStarted();
  }

  @Override
  public void stopSocketIO() {

  }

}
