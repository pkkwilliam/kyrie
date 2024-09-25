package mo.bitcode.kyrie.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum KyrieExceptionCode {

  TEAM_NOT_ALLOW_TO_ENTER_POOL(HttpStatus.BAD_REQUEST, "400.001", "Team is already in other process"),
  TEAM_NOT_EXISTED(HttpStatus.INTERNAL_SERVER_ERROR, "500.001", "Team does not existed"),
  TEAM_NOT_IN_POOL(HttpStatus.INTERNAL_SERVER_ERROR, "500.002", "Team not exist in pool"),
  TEAM_ALREADY_IN_POOL(HttpStatus.INTERNAL_SERVER_ERROR, "500.003", "Team already existed in pool");

  private HttpStatus httpStatus;
  private String errorCode;
  private String message;

  KyrieExceptionCode(HttpStatus httpStatus, String errorCode, String message) {
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.message = message;
  }

}
