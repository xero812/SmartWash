package com.smartwash.model;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Mapping {

    private String color;
    private String superColor;
    private Map<String, String> stringStringMap = new HashMap<>();
    private String filename = "./resources/colorSuperColorMapping.json";

    public Mapping() throws IOException {
        transformJSON();
    }

    public Map<String, String> getStringStringMap() {
        return stringStringMap;
    }

    public void setStringStringMap(Map<String, String> stringStringMap) {
        this.stringStringMap = stringStringMap;
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

    public  void transformJSON() throws IOException {
        File file = new File("app/sampledata/colorSuperColorMapping.json");
        FileReader fileReader = new FileReader(file);
        JsonReader jsonReader = new JsonReader(fileReader);
        Mapping[] mappings = new Gson().fromJson(jsonReader, Mapping[].class);
        Map<String, String> stringMap = new HashMap<>();
        for (Mapping mapping : mappings){
            stringMap.put(mapping.getColor(), mapping.getSuperColor());
        }
        this.stringStringMap = stringMap;
    }
}
