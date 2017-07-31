package com.slamur.app.deckwarlords.core.info;

import com.slamur.app.deckwarlords.core.CardInfo;

public abstract class CardInfoImpl implements CardInfo {

    private final String name;

    protected CardInfoImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String toUserString(int stars) {
        return toString();
    }
}
