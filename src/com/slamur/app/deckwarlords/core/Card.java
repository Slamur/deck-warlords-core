package com.slamur.app.deckwarlords.core;

public interface Card <CardType extends CardInfo> {

    CardType getCard();

    String getName();
    int getStars();

    boolean isParent(Card<?> other);

    String toUserString();
}
