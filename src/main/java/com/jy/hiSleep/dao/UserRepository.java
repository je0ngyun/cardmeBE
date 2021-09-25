package com.jy.hiSleep.dao;

import com.jy.hiSleep.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
