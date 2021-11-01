package com.jy.cardme.components.card;

import com.jy.cardme.entity.CardEntity;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public abstract class Card {

    protected Document doc;

    public abstract String getSvgString();

    public enum CardType {
        WhiteDefault
    }

    public static Card CardFactory(CardType cardType, CardEntity cardEntity) throws IOException {
        final Card card;
        switch (cardType) {
            case WhiteDefault:
                card = new WhiteDefaultCard();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cardType);
        }
        card.setCardTitle(cardEntity.getCardTitle());
        return card;
    }

    public void setCardTitle(String title) {
        final Element cardTitle = doc.getElementById("card-title");
        cardTitle.text(title);
    }
}
