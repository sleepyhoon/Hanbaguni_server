package com.hanbaguni.project.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorResult {
    NOT_LOGIN(HttpStatus.UNAUTHORIZED,"You have to login"),
    SOMETHING_NULL(HttpStatus.BAD_REQUEST,"You have to write all information"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
