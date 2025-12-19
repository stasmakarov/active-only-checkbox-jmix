package com.digitilius.jmix.addon.component;

public enum OrderDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String direction;

    OrderDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
