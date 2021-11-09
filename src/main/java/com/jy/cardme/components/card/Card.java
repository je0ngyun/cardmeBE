package com.jy.cardme.components.card;

import com.jy.cardme.entity.CardEntity;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
        card.setCardHighlightColor(cardEntity.getCardHighlightColor());
        card.setCardSkills(cardEntity.cardSkillsToList());
        return card;
    }

    public void setCardTitle(String title) {
        final Element cardTitle = doc.getElementById("card-title");
        cardTitle.text(title);
    }

    public void setCardMotto(String motto) {
        final Element cardMotto = doc.getElementById("card-motto");
        cardMotto.text(motto);
    }

    public void setCardEmail(String email) {
        final Element cardEmail = doc.getElementById("card-email");
        cardEmail.text(email);
    }

    public void setCardDepartment(String department) {
        final Element cardDepartment = doc.getElementById("card-department");
        cardDepartment.text(department);
    }

    public void setCardHighlightColor(String highlightColor) {
        final Elements cardHighlight = doc.getElementsByClass("highlight");
        for (Element element : cardHighlight) {
            element.attr("style", String.format("fill:%s", highlightColor));
        }
    }

    public void setCardSkills(List<String> skills) throws IOException {
        final InputStream in = getClass().getResourceAsStream("/static/CardSkillFragment.svg");
        Document cardSkillFragment = Jsoup.parse(IOUtils.toString(in, "UTF-8"));
        final Element infoContainer = doc.getElementById("info-container");
        final Element cardSkill = cardSkillFragment.body().child(0);
        infoContainer.appendChild(cardSkill);
    }

    public String getSvgString() {
        return doc.body().child(0).toString().replaceAll("viewbox", "viewBox");
    }
}
