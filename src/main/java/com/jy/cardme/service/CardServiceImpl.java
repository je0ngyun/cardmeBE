package com.jy.cardme.service;

import com.jy.cardme.components.card.Card;
import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.dao.CardRepository;
import com.jy.cardme.dto.CardDto;
import com.jy.cardme.entity.CardEntity;
import com.jy.cardme.entity.UserEntity;
import com.jy.cardme.exception.Common404Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserService userService;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserService userService) {
        this.cardRepository = cardRepository;
        this.userService = userService;
    }

    @Override
    public String generatingCard(CardDto.UseReq cardUseReq) throws IOException {
        final UserEntity user = userService.getUserEntity(cardUseReq.getUserId());
        final Optional<CardEntity> optional = cardRepository.findByUserAndCardName(user, cardUseReq.getCardName());
        if (!optional.isPresent()) {
            throw new Common404Exception(ResponseMessage.NOT_FOUND_CARD);
        }
        return Card.CardFactory(optional.get()).getSvgString();
    }

    @Transactional
    @Override
    public CardDto.Info signCard(final CardDto.SignReq cardSignReq) {
        final UserEntity user = userService.getUserEntity(cardSignReq.getUserId());
        final CardEntity card = CardEntity.builder()
                .user(user)
                .cardName(cardSignReq.getCardName())
                .cardTitle(cardSignReq.getCardTitle())
                .cardMotto(cardSignReq.getCardMotto())
                .cardEmail(cardSignReq.getCardEmail())
                .cardDepartment(cardSignReq.getCardDepartment())
                .cardSkills(cardSignReq.cardSkillsToStr())
                .cardType(Enum.valueOf(Card.CardType.class, cardSignReq.getCardType()))
                .cardHighlightColor(cardSignReq.getCardHighlightColor())
                .build();
        final CardEntity repoRet = cardRepository.save(card);
        final CardDto.Info cardSignRes = CardDto.Info.createFromEntity(repoRet);
        return cardSignRes;
    }
}
