package com.slamur.app.deckwarlords.core.info.token;

public enum TokenType {

    NONE(""), ADDITIVE("+"), MULTIPLICATIVE("%");

    private String alias;

    TokenType(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }
}
