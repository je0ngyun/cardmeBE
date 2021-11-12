package com.jy.cardme.components.card;

import com.jy.cardme.entity.CardEntity;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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
        card.setCardSkills(cardEntity.cardSkillsToList(), cardEntity.getCardHighlightColor());
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

    public void setCardSkills(List<String> skills,String highlightColor) {
        final Element infoContainer = doc.getElementById("info-container");
        ArrayList<ArrayList<String>> lineOfSkills = splitSkillsList(skills);
        Element skillFragment = getSkillFragment();
        int maximumSkillsLines = getMaximumSkillsLine();
        float skillStartYPos = getSkillStartYPos();
        float skillStartXPos = 0;
        float yPosIncrease = getYPosIncrease();

        for (int i = 0; i < maximumSkillsLines; i++) {
            for (String skillName : lineOfSkills.get(i)) {
                skillFragment.attr("transform", String.format("translate(%s,%s)", skillStartXPos, skillStartYPos));
                skillFragment.getElementById("skill-rect").attr("width", String.format("%s", getSkillRectWidth(skillName)));
                skillStartXPos += getSkillRectWidth(skillName)+3;
                skillFragment.getElementById("skill-rect").attr("fill", String.format("%s",highlightColor));
                skillFragment.getElementById("skill-name").text(skillName);
                infoContainer.append(skillFragment.toString());
            }
            skillStartYPos += yPosIncrease;
            skillStartXPos = 0;
        }
    }

    public int getNumberOfLettersAllowed() {
        final Element main = doc.getElementById("main");
        int mainWidth = Integer.parseInt(main.attr("width"));
        return 33 * mainWidth / 380;
    }

    public float getSkillStartYPos() {
        Element helperNode = doc.getElementById("skill-pos-helper");
        String transform = helperNode.attr("transform");
        float y = Integer.parseInt(transform.substring(transform.length() - 3, transform.length() - 1));
        return y;
    }

    public float getSkillRectWidth(String skillName) {
        return skillName.length() * 6 + 17;
    }

    public int getMaximumSkillsLine() {
        Element posHelper = doc.getElementById("skill-pos-helper");
        String data = posHelper.attr("class");
        return Integer.parseInt(data.split(",")[0]);
    }

    public float getYPosIncrease() {
        Element posHelper = doc.getElementById("skill-pos-helper");
        String data = posHelper.attr("class");
        return Float.parseFloat(data.split(",")[1]);
    }

    public Element getSkillFragment() {
        Element skillFragment = doc.getElementById("skill-pos-helper").child(0);
        doc.getElementById("skill-pos-helper").child(0).remove();
        return skillFragment;
    }

    public ArrayList<ArrayList<String>> splitSkillsList(List<String> skills) {
        Stack<String> skillsStack = new Stack<>();
        Collections.reverse(skills);
        skillsStack.addAll(skills);

        int numberOfLettersAllowed = getNumberOfLettersAllowed();
        int currentLetters = 0;
        ArrayList<ArrayList<String>> lineOfSkillsList = new ArrayList<>();

        while (!skillsStack.isEmpty()) {
            ArrayList<String> lineOfSkill = new ArrayList<>();
            while (!skillsStack.isEmpty()) {
                String skill = skillsStack.pop();
                currentLetters += skill.length();
                if (currentLetters > numberOfLettersAllowed) {
                    skillsStack.push(skill);
                    break;
                }
                lineOfSkill.add(skill);
            }
            currentLetters = 0;
            lineOfSkillsList.add(new ArrayList<>(lineOfSkill));
            lineOfSkill.clear();
        }
        return lineOfSkillsList;
    }

    public String getSvgString() {
        return doc.body().child(0).toString().replaceAll("viewbox", "viewBox");
    }
}
