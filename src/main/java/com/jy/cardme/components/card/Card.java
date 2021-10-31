package com.jy.cardme.components.card;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public abstract class Card {

    protected Document doc;

    public abstract String getSvgString();

    public enum CardType {
        WhiteDefault
    }

    public static Card CardFactory(CardType cardType) throws IOException { //card info dto 매개변수 추가 필요
        Card card;
        switch (cardType) {
            case WhiteDefault:
                card = new WhiteDefaultCard();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cardType);
        }
        card.setCardTitle();
        return card;
    }

    //card info dto 기반으로 Svg 추가처리 필요
    public void setCardTitle() {
        Element title = doc.getElementById("card-title");
        title.text("test");
    }
}
