package com.smartwash.model;


import java.io.Serializable;
import java.util.List;

public class Bucket implements Serializable{

    private static final long serialVersionUID = 1L;
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
