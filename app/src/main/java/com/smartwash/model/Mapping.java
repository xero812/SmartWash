package com.smartwash.model;

import android.os.Environment;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Mapping {

    private String color;
    private String superColor;
    private Map<String, String> colorSuperColorMap = new HashMap<>();

    public Map<String, String> getColorSuperColorMap() {
        return colorSuperColorMap;
    }

    public void setColorSuperColorMap(Map<String, String> colorSuperColorMap) {
        this.colorSuperColorMap = colorSuperColorMap;
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

    public void transformJSON(InputStream file) {
        try {
            InputStreamReader fileReader = new InputStreamReader(file);
            JsonReader jsonReader = new JsonReader(fileReader);
            Mapping[] mappings = new Gson().fromJson(jsonReader, Mapping[].class);
            Map<String, String> stringMap = new HashMap<>();
            for (Mapping mapping : mappings) {
                stringMap.put(mapping.getColor(), mapping.getSuperColor());
            }
            this.colorSuperColorMap = stringMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
