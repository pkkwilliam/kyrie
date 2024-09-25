package mo.bitcode.kyrie.service.team.impl;

import mo.bitcode.kyrie.service.application_config.ApplicationConfigService;
import mo.bitcode.kyrie.repo.mongo.TeamMongoDbRepository;
import mo.bitcode.kyrie.service.team.TeamServiceTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl extends TeamServiceTemplate {

  private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);

  public TeamServiceImpl(ApplicationConfigService applicationConfigService,
                         TeamMongoDbRepository teamMongoDbRepository) {
    super(applicationConfigService, teamMongoDbRepository);
  }

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

}
