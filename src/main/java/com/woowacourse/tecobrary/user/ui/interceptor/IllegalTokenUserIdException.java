package com.woowacourse.tecobrary.user.ui.interceptor;

public class IllegalTokenUserIdException extends RuntimeException {

    public static final String ILLEGAL_TOKEN_USER_ID_EXCEPTION_MESSAGE = "권한이 없는 사용자 입니다. [NSU-0001]";

    public IllegalTokenUserIdException() {
        super(ILLEGAL_TOKEN_USER_ID_EXCEPTION_MESSAGE);
    }
}
