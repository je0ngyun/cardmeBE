package com.jy.hiSleep.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
@Getter
@Builder(builderMethodName = "userEntityBuilder",access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserEntity {
    @Id
    private String userID;
    @Column(nullable = false)
    private String userPw;
    @Column(nullable = false)
    private String userNm;
    @Column(nullable = false)
    private String userEm;

    public static UserEntityBuilder builder(String id) {
        if(id == null) {
            throw new IllegalArgumentException("필수 파라미터 누락");
        }
        return userEntityBuilder().userID(id);
    }
}
