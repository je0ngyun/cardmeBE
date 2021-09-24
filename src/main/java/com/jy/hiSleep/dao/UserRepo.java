package com.jy.hiSleep.dao;

import com.jy.hiSleep.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, String> {
}
