package com.smartwash.model;


import java.util.List;

public class Bucket {

    private int id;
    private List<Fabric> fabrics;


    public Bucket(int id, List<Fabric> fabrics) {
        this.id = id;
        this.fabrics = fabrics;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFabrics(List<Fabric> fabrics) {
        this.fabrics = fabrics;
    }

    public int getId() {
        return id;
    }

    public List<Fabric> getFabrics() {
        return fabrics;
    }


}
