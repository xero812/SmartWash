package com.smartwash.model;

import java.util.Set;

public class Classifier {

    private Integer bucketNo;
    private Set<Classifier> classifiers;

    public Integer getBucketNo() {
        return bucketNo;
    }

    public void setBucketNo(Integer bucketNo) {
        this.bucketNo = bucketNo;
    }

    public Set<Classifier> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(Set<Classifier> classifiers) {
        this.classifiers = classifiers;
    }
}
