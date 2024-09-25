package mo.bitcode.kyrie.service.score.impl;

import mo.bitcode.kyrie.service.cross_confirmation.model.TeamAssertScore;
import mo.bitcode.kyrie.service.score.ScoreService;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateResult;
import org.springframework.stereotype.Service;

@Service
public class ScoreServiceImpl implements ScoreService {

  @Override
  public ScoreCalculateResult calculateScore(TeamAssertScore homeTeamAssert, TeamAssertScore awayTeamAssert) {
    return null;
  }

}
