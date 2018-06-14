package com.smartwash.model;

import java.util.Objects;

public class ClassifierKey {

    private String classifierName;
    private String classifierType;


    public ClassifierKey(String classifierName, String classifierType) {
        this.classifierName = classifierName;
        this.classifierType = classifierType;
    }

    public String getClassifierName() {
        return classifierName;
    }

    public String getClassifierType() {
        return classifierType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassifierKey that = (ClassifierKey) o;
        return Objects.equals(classifierName, that.classifierName) &&
                Objects.equals(classifierType, that.classifierType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classifierName, classifierType);
    }
}
