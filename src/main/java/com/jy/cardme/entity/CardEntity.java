package com.jy.cardme.entity;

import com.jy.cardme.components.card.Card;
import lombok.*;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "card")
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CardEntity {
    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private String cardName;

    @Enumerated(EnumType.STRING)
    private Card.CardType cardType;

    @Column(nullable = false)
    private String cardTitle;

    @Column(nullable = false)
    private String cardMotto;

    @Column(nullable = false)
    private String cardEmail;

    @Column(nullable = false)
    private String cardDepartment;

    @Column(nullable = false)
    private String cardSkills;

    @Column(nullable = false)
    private String cardHighlightColor;

    public List<String> cardSkillsToList(){
        return Arrays.asList(cardSkills.split(","));
    }
}
