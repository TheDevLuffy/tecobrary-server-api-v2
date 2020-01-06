package com.woowacourse.tecobrary.user.command.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotAuthorizedUserException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(NotAuthorizedUserException.class);
    private static final String NOT_AUTHORIZED_USER_EXCEPTION_MESSAGE = "권한이 없습니다.";

    public NotAuthorizedUserException(final Authorization authorization) {
        super(NOT_AUTHORIZED_USER_EXCEPTION_MESSAGE);
        log.info("Authorization Level : {}", authorization.name());
    }
}
