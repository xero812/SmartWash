package com.smartwash.model;

public enum FabricAttribute {
    TYPE("Type"),
    MATERIAL("Material"),
    SUPER_COLOR("SuperColor");

    private String value;

    FabricAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
