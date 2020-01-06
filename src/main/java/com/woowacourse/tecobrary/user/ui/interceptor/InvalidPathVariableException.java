package com.woowacourse.tecobrary.user.ui.interceptor;

public class InvalidPathVariableException extends RuntimeException {

    public static final String INVALID_PATH_VARIABLE_EXCEPTION_MESSAGE = "유효하지 않은 요청입니다. IPV-0001";

    public InvalidPathVariableException() {
        super(INVALID_PATH_VARIABLE_EXCEPTION_MESSAGE);
    }
}
