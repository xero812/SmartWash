package com.smartwash.model;

import java.io.Serializable;

public class Fabric implements Serializable {

    private static final long serialVersionUID = 2L;

    private String type;
    private String material;
    private String color;
    private String superColor;
    private Integer bucketId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSuperColor() {
        return superColor;
    }

    public void setSuperColor(String superColor) {
        this.superColor = superColor;
    }

    public Integer getBucketId() {
        return bucketId;
    }

    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }

    @Override
    public String toString() {
        return "Fabric{" +
                "type='" + type + '\'' +
                ", material='" + material + '\'' +
                ", color='" + color + '\'' +
                ", superColor='" + superColor + '\'' +
                ", bucketId=" + bucketId +
                '}';
    }
}
