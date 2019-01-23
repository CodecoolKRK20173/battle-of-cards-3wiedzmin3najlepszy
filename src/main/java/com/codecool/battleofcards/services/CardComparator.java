package com.codecool.battleofcards.services;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    private AttributeType attributeType;

    CardComparator(AttributeType attributeType) {
        this.attributeType = attributeType;
    }

    @Override
    public int compare(Card cardOne, Card cardTwo) {
        int attrOne = getAttributeOf(cardOne);
        int attrTwo = getAttributeOf(cardTwo);

        if (attrOne > attrTwo) {
            return 1;
        } else if (attrOne == attrTwo) {
            return 0;
        } else {
            return -1;
        }
    }

    private int getAttributeOf(Card card) {
        int attrValue = 0;

        switch (attributeType) {
        case STRENGTH:
            attrValue = card.getStrength();
            break;
        case MELEE:
            attrValue = card.getMelee();
            break;
        case MAGIC:
            attrValue = card.getMagic();
            break;
        case DEXTERITY:
            attrValue = card.getDexterity();
            break;
        case INTELLIGENCE:
            attrValue = card.getIntelligence();
            break;
        }

        return attrValue;
    }
}

enum AttributeType {
    STRENGTH, MELEE, MAGIC, DEXTERITY, INTELLIGENCE
}