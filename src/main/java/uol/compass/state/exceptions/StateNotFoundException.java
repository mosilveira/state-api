package uol.compass.state.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import uol.compass.state.constants.ErrorCode;

@Getter
public class StateNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String details;
    private final ErrorCode errorCode;
    private final HttpStatus httpStatus;

    public StateNotFoundException() {
        super(ErrorCode.STATE_NOT_FOUND.name());
        this.httpStatus = HttpStatus.NOT_FOUND;
        this.errorCode = ErrorCode.STATE_NOT_FOUND;
        this.details = ErrorCode.STATE_NOT_FOUND.getMessage();
    }

}
