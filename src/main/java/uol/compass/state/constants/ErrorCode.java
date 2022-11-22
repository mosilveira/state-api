package uol.compass.state.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    STATE_NOT_FOUND("State not found"),
    BAD_REQUEST("Request invalid"),
    INVALID_PARAMETER("Invalid request parameter"),
    INTERNAL_SERVER_ERROR("Internal error has occurred.");

    private final String message;

}
