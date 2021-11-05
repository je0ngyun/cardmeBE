package com.jy.cardme.entity;

import com.jy.cardme.components.card.Card;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "card")
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class CardEntity {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
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
    private String cardSkill;

    @Column(nullable = false)
    private String cardHighlightColor;

}
