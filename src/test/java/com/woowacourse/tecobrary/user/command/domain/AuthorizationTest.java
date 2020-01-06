package com.woowacourse.tecobrary.user.command.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorizationTest {

    @DisplayName("NONE 은 USER 권한을 가지지 않는다.")
    @Test
    void noneHasNotUserPermission() {
        assertThrows(NotAuthorizedUserException.class, Authorization.NONE::hasUserPermission);
    }

    @DisplayName("USER 은 USER 권한을 가진다.")
    @Test
    void userHasUserPermission() {
        assertTrue(Authorization.USER.hasUserPermission());
    }

    @DisplayName("MANAGER 은 USER 권한을 가진다.")
    @Test
    void managerHasUserPermission() {
        assertTrue(Authorization.MANAGER.hasUserPermission());
    }

    @DisplayName("KING 은 USER 권한을 가진다.")
    @Test
    void kingHasUserPermission() {
        assertTrue(Authorization.KING.hasUserPermission());
    }

    @DisplayName("NONE 은 MANAGER 권한을 가지지 않는다.")
    @Test
    void noneHasNotManagerPermission() {
        assertThrows(NotAuthorizedUserException.class, Authorization.NONE::hasManagerPermission);
    }

    @DisplayName("USER 은 MANAGER 권한을 가지지 않는다.")
    @Test
    void userHasNotManagerPermission() {
        assertThrows(NotAuthorizedUserException.class, Authorization.USER::hasManagerPermission);
    }

    @DisplayName("MANAGER 은 MANAGER 권한을 가진다.")
    @Test
    void managerHasManagerPermission() {
        assertTrue(Authorization.MANAGER.hasManagerPermission());
    }

    @DisplayName("KING 은 MANAGER 권한을 가진다.")
    @Test
    void kingHasManagerPermission() {
        assertTrue(Authorization.KING.hasManagerPermission());
    }
}