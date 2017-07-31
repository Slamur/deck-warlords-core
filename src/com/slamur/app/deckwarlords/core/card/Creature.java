package com.slamur.app.deckwarlords.core.card;

import com.slamur.app.deckwarlords.core.info.Attribute;
import com.slamur.app.deckwarlords.core.Card;
import com.slamur.app.deckwarlords.core.info.CreatureInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creature extends CardImpl<CreatureInfo> implements Card<CreatureInfo> {

    private Map<Attribute, Double> attributeMap;
    private List<Token> tokens;

    public Creature(CreatureInfo creature, int stars) {
        super(creature, stars);

        this.attributeMap = new HashMap<>();

        this.tokens = new ArrayList<>();
        for (int index = 0; index < getMaxTokens(); ++index) {
            tokens.add(null);
        }

        updateAttributes();
    }

    public int getMaxTokens() {
        return card.getMaxTokens(stars);
    }

    private int toIntValue(double value) {
        return (int)Math.round(value);
    }

    public int getAttributeValue(Attribute attribute) {
        return toIntValue(attributeMap.get(attribute));
    }

    private void updateAttributes() {
        for (Attribute attribute : Attribute.values()) {
            attributeMap.put(
                    attribute, card.getAttributeValue(attribute, stars) * 1.0
            );
        }

        for (Token token : tokens) {
            if (null == token) continue;

            Attribute tokenAttribute = token.getCard().getAttribute();

            double oldAttributeValue = attributeMap.get(tokenAttribute);

            double nextAttributeValue = oldAttributeValue + token.getAdditionalPart();
            nextAttributeValue = nextAttributeValue * token.getMultiplier();
            nextAttributeValue = toIntValue(nextAttributeValue);

            attributeMap.put(tokenAttribute, nextAttributeValue);
        }
    }

    public Token getToken(int index) {
        return (index < tokens.size() ? tokens.get(index) : null);
    }

    public void setToken(int index, Token token) {
        if (index < tokens.size()) {
            tokens.set(index, token);
            updateAttributes();
        }
    }
}
