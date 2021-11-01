package com.jy.cardme.dao;

import com.jy.cardme.entity.CardEntity;
import com.jy.cardme.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity> findByUserAndCardName(UserEntity user, String cardName);
}
