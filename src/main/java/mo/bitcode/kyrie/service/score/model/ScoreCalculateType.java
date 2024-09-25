package mo.bitcode.kyrie.service.score.model;

public enum ScoreCalculateType {

  AGREE, //Both Score and Result are the same
  RESULT_DISCREPANCY_RETRY, // Result
  SCORE_DISCREPANCY, // Score Different but Win/Lose are the same. Game can consider as finished
  RESULT_CONFLICT_FORCE_CALCULATED, // Retry exhausted, Score and Result are both Different, need Credit calculation
}
