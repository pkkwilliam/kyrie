package mo.bitcode.kyrie.common.exception;

import mo.bitcode.core.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class KyrieException extends ApplicationException {

  public KyrieException(KyrieExceptionCode kyrieExceptionCode) {
    super(kyrieExceptionCode.getHttpStatus(), kyrieExceptionCode.getErrorCode(), kyrieExceptionCode.getMessage());
  }

  public KyrieException(HttpStatus httpStatus, String errorCode, String message) {
    super(httpStatus, errorCode, message);
  }

}
