package com.jy.cardme.components.card;

import com.jy.cardme.entity.CardEntity;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public abstract class Card {

    protected Document doc;

    public enum CardType {
        WHITE_DEFAULT
    }

    public static Card CardFactory(CardEntity cardEntity) throws IOException {
        final Card card;
        switch (cardEntity.getCardType()) {
            case WHITE_DEFAULT:
                card = new WhiteDefaultCard();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cardEntity.getCardType());
        }
        card.setCardTitle(cardEntity.getCardTitle());
        card.setCardMotto(cardEntity.getCardMotto());
        card.setCardEmail(cardEntity.getCardEmail());
        card.setCardDepartment(cardEntity.getCardDepartment());
        return card;
    }

    public void setCardTitle(String title) {
        final Element cardTitle = doc.getElementById("card-title");
        cardTitle.text(title);
    }

    public void setCardMotto(String motto){
        final Element cardMotto = doc.getElementById("card-motto");
        cardMotto.text(motto);
    }

    public void setCardEmail(String email){
        final Element cardEmail = doc.getElementById("card-email");
        cardEmail.text(email);
    }

    public void setCardDepartment(String department){
        final Element cardDepartment = doc.getElementById("card-department");
        cardDepartment.text(department);
    }

    public void setCardSkill(String skill){
        final Element cardSkill = doc.getElementById("card-skill");
        // 추후구현
    }

    public String getSvgString(){
        return doc.body().child(0).toString().replaceAll("viewbox","viewBox");
    }
}
