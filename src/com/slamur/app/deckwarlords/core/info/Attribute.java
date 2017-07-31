package com.slamur.app.deckwarlords.core.info;

public enum Attribute {

    HEALTH("HP"), DAMAGE("ATK"), INITIATIVE("INI"), ARMOR("ARM"), THORNS("TRN");

    private String alias;

    Attribute(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
