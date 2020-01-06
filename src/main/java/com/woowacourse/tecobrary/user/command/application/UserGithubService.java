/*
 * @(#) UserGithubService.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.application;

import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.command.domain.User;
import com.woowacourse.tecobrary.user.command.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.command.util.UserGithubInfoMapper;
import com.woowacourse.tecobrary.user.command.util.UserJwtDtoMapper;
import com.woowacourse.tecobrary.user.ui.dto.UserJwtInfoDto;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGithubService {

    private final UserService userService;
    private final GithubApiService githubApiService;

    @Autowired
    public UserGithubService(final UserService userService, final GithubApiService githubApiService) {
        this.userService = userService;
        this.githubApiService = githubApiService;
    }

    public UserJwtInfoDto getUserByGithubInfo(final String githubApiAccessToken) {
        GithubUserInfoVo githubUserInfoVo = githubApiService.getGithubUserInfo(githubApiAccessToken);
        try {
            return UserJwtDtoMapper.toDto(userService.findByGithubId(githubUserInfoVo.getId()));
        } catch (NotFoundGithubUserException e) {
            return UserJwtDtoMapper.toDto(getNewUserAfterSave(githubApiAccessToken, githubUserInfoVo));
        }
    }

    private User getNewUserAfterSave(final String githubApiAccessToken, final GithubUserInfoVo githubUserInfoVo) {
        UserGithubInfo userGithubInfo = UserGithubInfoMapper.toDomain(githubUserInfoVo,
                githubApiService.getGithubUserEmail(githubApiAccessToken));
        return userService.save(userGithubInfo);
    }
}
