package com.jy.cardme.service;

import com.jy.cardme.components.card.Card;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CardServiceImpl implements CardService {
    @Override
    public String generatingCard() throws IOException {
        return Card.CardFactory(Card.CardType.WhiteDefault).getSvgString();
    }
}
