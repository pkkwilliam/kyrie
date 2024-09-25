package mo.bitcode.kyrie.service.court.impl;

import mo.bitcode.kyrie.repo.mongo.CourtMongoDbRepository;
import mo.bitcode.kyrie.service.court.CourtServiceTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourtServiceImpl extends CourtServiceTemplate {

  public CourtServiceImpl(CourtMongoDbRepository repository) {
    super(repository);
  }

}
