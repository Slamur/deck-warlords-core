package com.slamur.app.deckwarlords.core.info.creature;

import com.slamur.app.deckwarlords.core.info.Attribute;
import com.slamur.app.deckwarlords.core.info.CardInfoImpl;
import com.slamur.app.deckwarlords.core.info.CreatureInfo;

import java.util.HashMap;
import java.util.Map;

public class CreatureImpl extends CardInfoImpl implements CreatureInfo {

    private final int[] maxTokens;
    private final Map<Attribute, int[]> attributeMap;

    CreatureImpl(String name, int... maxTokens) {
        super(name);
        this.maxTokens = maxTokens;

        this.attributeMap = new HashMap<>();
        for (Attribute attribute : Attribute.values()) {
            setAttributeValue(attribute, 0);
        }
    }

    @Override
    public int getAttributeValue(Attribute attribute, int stars) {
        return attributeMap.get(attribute)[stars];
    }

    @Override
    public CreatureInfo setAttributeValues(Attribute attribute, int... values) {
        attributeMap.put(attribute, values);
        return this;
    }

    @Override
    public int getMaxTokens(int stars) {
        return maxTokens[stars];
    }
}
