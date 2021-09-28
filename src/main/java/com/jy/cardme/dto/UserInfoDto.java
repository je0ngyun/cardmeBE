package com.jy.cardme.dto;

import com.jy.cardme.entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
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
