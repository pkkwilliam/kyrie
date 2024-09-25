package mo.bitcode.kyrie.service.score;

import mo.bitcode.kyrie.service.cross_confirmation.model.TeamAssertScore;
import mo.bitcode.kyrie.service.score.model.ScoreCalculateResult;

public interface ScoreService {

  ScoreCalculateResult calculateScore(TeamAssertScore homeTeamAssert, TeamAssertScore awayTeamAssert);

}
