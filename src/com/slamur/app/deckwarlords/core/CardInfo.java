package com.slamur.app.deckwarlords.core;

public interface CardInfo {

    String getName();
    int getMaxStars();

    String toUserString(int stars);
}