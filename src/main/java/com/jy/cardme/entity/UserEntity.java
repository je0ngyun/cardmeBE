package com.jy.cardme.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Data
@Builder(builderMethodName = "userEntityBuilder")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class UserEntity implements UserDetails {
    @Id
    private String userId;
    @Column(nullable = false)
    private String userPw;
    @Column(nullable = false)
    private String userNm;
    @Column(nullable = false)
    private String userEm;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public static UserEntityBuilder builder(final String id) {
        if(id == null) {
            throw new IllegalArgumentException("필수 파라미터 누락");
        }
        return userEntityBuilder().userId(id);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
