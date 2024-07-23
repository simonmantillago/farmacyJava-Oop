package com.farmacysystem.identificationtypes.domain.entity;

public class IdentificationType {
    private String description;

    public IdentificationType() {
    }

    public IdentificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
