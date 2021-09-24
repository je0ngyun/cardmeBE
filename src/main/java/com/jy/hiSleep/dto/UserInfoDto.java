package com.jy.hiSleep.dto;

import com.jy.hiSleep.entity.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserInfoDto {
    private String userID;
    private String userNm;
    private String userEm;
    public static UserInfoDto create(final UserEntity user) {
        return builder()
                .userID(user.getUserID())
                .userNm(user.getUserNm())
                .userEm(user.getUserEm())
                .build();
    }
}
