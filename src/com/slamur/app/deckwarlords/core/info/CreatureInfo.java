package com.slamur.app.deckwarlords.core.info;

import com.slamur.app.deckwarlords.core.CardInfo;

import java.util.Arrays;

public interface CreatureInfo extends CardInfo {

    int CREATURE_MAX_STARS = 3;

    int getMaxTokens(int stars);

    int getAttributeValue(Attribute attribute, int stars);

    CreatureInfo setAttributeValues(Attribute attribute, int... values);
    default CreatureInfo setAttributeValue(Attribute attribute, int value) {
        int[] values = new int[getMaxStars() + 1];
        Arrays.fill(values, value);

        return setAttributeValues(attribute, values);
    }

    @Override
    default int getMaxStars() {
        return CREATURE_MAX_STARS;
    }
}
