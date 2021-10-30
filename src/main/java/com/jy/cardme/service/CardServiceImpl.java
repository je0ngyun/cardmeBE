package com.jy.cardme.service;

import com.jy.cardme.components.card.Card;
import com.jy.cardme.dao.CardRepository;
import com.jy.cardme.dto.CardDto;
import com.jy.cardme.entity.CardEntity;
import com.jy.cardme.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public String generatingCard() throws IOException {
//        Test
//        UserEntity user = UserEntity.builder("kjyjh9930").build();
//        CardEntity card = cardRepository.findByUserAndCardName(user,"default");
//        System.out.println(card.getCardEmail());
        return Card.CardFactory(Card.CardType.WhiteDefault).getSvgString();
    }

    @Override
    public CardDto.SignRes signCard(final CardDto.SignReq cardSignReq){
        final UserEntity temp = UserEntity.userEntityBuilder()
                .userId(cardSignReq.getUserId())
                .build();
        final CardEntity card = CardEntity.builder()
                .user(temp)
                .cardName(cardSignReq.getCardName())
                .cardTitle(cardSignReq.getCardTitle())
                .cardMotto(cardSignReq.getCardMotto())
                .cardEmail(cardSignReq.getCardEmail())
                .cardDepartment(cardSignReq.getCardDepartment())
                .cardSkill(cardSignReq.getCardSkill())
                .build();
        final CardEntity repoRet = cardRepository.save(card);
        final CardDto.SignRes cardSignRes = CardDto.SignRes.createFromEntity(repoRet);
        return cardSignRes;
    }
}
