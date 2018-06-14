package com.smartwash.model;

import java.util.List;

public class Mapping {
    private String parent;
    private List<String> child;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<String> getChild() {
        return child;
    }

    public void setChild(List<String> child) {
        this.child = child;
    }
}
