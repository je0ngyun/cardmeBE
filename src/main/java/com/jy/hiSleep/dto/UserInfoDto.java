package com.jy.hiSleep.dto;

import com.jy.hiSleep.entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class UserInfoDto {
    private String userId;
    private String userNm;
    private String userEm;
    public static UserInfoDto create(final UserEntity user) {
        return builder()
                .userId(user.getUserId())
                .userNm(user.getUserNm())
                .userEm(user.getUserEm())
                .build();
    }
}
