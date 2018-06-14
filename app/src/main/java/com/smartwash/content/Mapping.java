package com.smartwash.content;

import java.util.List;

public class Mapping {
    String parent;
    List<String> child;

    public Mapping(String parent) {
        this.parent = parent;
    }

    public void setChild(List<String> child) {
        this.child = child;
    }
}
