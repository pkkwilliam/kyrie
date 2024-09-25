package mo.bitcode.kyrie.service.application_init;

public interface ApplicationComponentService {

  void onApplicationStarted();
  void onApplicationPreDestroy();

}
