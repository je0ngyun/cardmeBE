package com.jy.cardme.service;

import com.jy.cardme.components.card.Card;
import com.jy.cardme.components.commons.ResponseMessage;
import com.jy.cardme.dao.CardRepository;
import com.jy.cardme.dto.CardDto;
import com.jy.cardme.entity.CardEntity;
import com.jy.cardme.entity.UserEntity;
import com.jy.cardme.exception.Common400Exception;
import com.jy.cardme.exception.Common404Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public String generatingCard(CardDto.UseReq cardUseReq) throws IOException {
        final UserEntity temp = UserEntity.builder(cardUseReq.getUserId()).build();
        final Optional<CardEntity> optional = cardRepository.findByUserAndCardName(temp,cardUseReq.getCardName());
        if (!optional.isPresent()) {
            throw new Common404Exception(ResponseMessage.NOT_FOUND_CARD);
        }
        return Card.CardFactory(optional.get()).getSvgString();
    }

    @Override
    public CardDto.Info signCard(final CardDto.SignReq cardSignReq) {
        final UserEntity temp = UserEntity.builder(cardSignReq.getUserId()).build();
        final Optional<CardEntity> optional = cardRepository.findByUserAndCardName(temp, cardSignReq.getCardName());
        if (optional.isPresent()) {
            throw new Common400Exception(ResponseMessage.DUPLICATE_CARD_NAME);
        }
        final CardEntity card = CardEntity.builder()
                .user(temp)
                .cardName(cardSignReq.getCardName())
                .cardTitle(cardSignReq.getCardTitle())
                .cardMotto(cardSignReq.getCardMotto())
                .cardEmail(cardSignReq.getCardEmail())
                .cardDepartment(cardSignReq.getCardDepartment())
                .cardSkills(cardSignReq.getCardSkills())
                .cardType(Enum.valueOf(Card.CardType.class,cardSignReq.getCardType()))
                .cardHighlightColor(cardSignReq.getCardHighlightColor())
                .build();
        final CardEntity repoRet = cardRepository.save(card);
        final CardDto.Info cardSignRes = CardDto.Info.createFromEntity(repoRet);
        return cardSignRes;
    }
}
