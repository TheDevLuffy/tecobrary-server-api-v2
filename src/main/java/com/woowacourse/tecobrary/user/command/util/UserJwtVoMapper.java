package com.woowacourse.tecobrary.user.command.util;

import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.ui.dto.UserJwtInfoDto;

public class UserJwtVoMapper {

    public static UserJwtInfoDto toVo(final User user) {
        return UserJwtInfoDto.builder()
                .id(user.getId())
                .email(user.getUserEmail())
                .name(user.getUserName())
                .avatarUrl(user.getUserAvatarUrl())
                .authorization(user.getAuthorization())
                .build();
    }
}
