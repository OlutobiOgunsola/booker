package com.tobi.booker.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, "No code", HttpStatus.NOT_IMPLEMENTED),
    INCORRECT_CURRENT_PASSWORD(300, "Invalid current password", HttpStatus.BAD_REQUEST),
    NEW_PASSWORD_DOES_NOT_MATCH(301, "The new password does not match", HttpStatus.BAD_REQUEST),
    ACCOUNT_LOCKED(302, "User account is locked", HttpStatus.FORBIDDEN),
    ACCOUNT_DISABLED(303, "User account is disabled", HttpStatus.FORBIDDEN),
    BAD_CREDENTIALS(304, "User credentials are incorrect", HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION(305, "Data integrity violated", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(310, "Internal error occured. Please contact the administrator", HttpStatus.INTERNAL_SERVER_ERROR),
    MAILING_ERROR(311, "Error sending email", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(312, "Operation unauthorized for user", HttpStatus.UNAUTHORIZED)
    ;

    private final int code;

    private final String description;

    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

}
