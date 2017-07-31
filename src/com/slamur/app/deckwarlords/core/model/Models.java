package com.slamur.app.deckwarlords.core.model;

public class Models {

    public static CreatureModel creatureModel;

    static {
        creatureModel = CreatureModel.createInstance();
    }
}
