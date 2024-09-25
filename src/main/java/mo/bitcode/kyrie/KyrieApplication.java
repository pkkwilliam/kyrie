package mo.bitcode.kyrie;

import jakarta.annotation.PreDestroy;
import mo.bitcode.kyrie.service.application_init.ApplicationInitService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({
  "mo.bitcode.core",
//				"mo.bitcode.web_socket",
  "mo.bitcode.kyrie",
//				"org.springframework.web.socket.config"
})
//@EnableJpaRepositories(basePackages = {"mo.bitcode.kyrie.repo.mysql"})
@EnableMongoRepositories(basePackages = {"mo.bitcode.kyrie.repo.mongo"})
@EntityScan({"mo.bitcode.core.entityModel", "mo.bitcode.kyrie", "mo.bitcode.kyrie.common.model", "mo.bitcode.kyrie.repo.mysql.model"})
@SpringBootApplication
@EnableScheduling
public class KyrieApplication {

  private ApplicationInitService applicationInitService;

  public KyrieApplication(ApplicationInitService applicationInitService) {
    this.applicationInitService = applicationInitService;
  }

  public static void main(String[] args) {
    SpringApplication.run(KyrieApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void startApplicationInitService() {
    this.applicationInitService.startSocketIO();
  }

  @PreDestroy
  public void onDestroy() {
    this.applicationInitService.stopSocketIO();
  }

}
