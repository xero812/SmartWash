package com.smartwash.model;

import java.util.Map;
import java.util.Objects;

public class Classifier {

    private Integer bucketNo;
    private ClassifierKey classifierKey;
    private Map<ClassifierKey, Classifier> classifiers;

    public Integer getBucketNo() {
        return bucketNo;
    }

    public void setBucketNo(Integer bucketNo) {
        this.bucketNo = bucketNo;
    }

    public Map<ClassifierKey, Classifier> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(Map<ClassifierKey, Classifier> classifiers) {
        this.classifiers = classifiers;
    }

    public ClassifierKey getClassifierKey() {
        return classifierKey;
    }

    public void setClassifierKey(ClassifierKey classifierKey) {
        this.classifierKey = classifierKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classifier that = (Classifier) o;
        return Objects.equals(classifierKey, that.classifierKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classifierKey);
    }
}
