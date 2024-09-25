package mo.bitcode.kyrie.common.bedrock.kafka;

import mo.bitcode.core.service.message_queue_v2.MessageQueueConsumerMessageHandler;
import mo.bitcode.kafka.consumer.KafkaConsumerService;
import mo.bitcode.kafka.producer.KafkaProducerService;
import mo.bitcode.kyrie.service.team.model.TeamDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KyrieKafkaConfiguration {

  private static final String TEAM_KAFKA_TOPIC_NAME = "MY_TEST_TOPIC";

  @Bean(value = "TeamDtoKafka")
  public KafkaProducerService<TeamDto> getKafkaProducerService(KafkaTemplate<String, TeamDto> kafkaTemplate) {
    return new KafkaProducerService<>(kafkaTemplate, TEAM_KAFKA_TOPIC_NAME);
  }

  @Bean
  public KafkaConsumerService<TeamDto> getKafkaConsumerService() {
    final MessageQueueConsumerMessageHandler<TeamDto> handler = message -> {
      System.out.println("RECEIVED: " + message);
    };
    return new KafkaConsumerService(TEAM_KAFKA_TOPIC_NAME, "TESTING_GROUP", handler, TeamDto.class);
  }

}
