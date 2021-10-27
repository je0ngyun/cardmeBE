package com.jy.cardme.components.card;

import java.io.IOException;

public abstract class Card {
    protected String SvgString;

    public abstract String getSvgString();

    public enum CardType {
        WhiteDefault, WhiteSmall
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
        card.test();
        return card;
    }

    //card info dto 기반으로 Svg 추가처리 필요
    public void test() {
        SvgString += "";
    }
}
