package com.smartwash.model;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.util.*;

public class Mapping {

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String superColor;

    public String getSuperColor() {
        return superColor;
    }

    public void setSuperColor(String superColor) {
        this.superColor = superColor;
    }

    private String filename = "../resources/colorSuperColorMapping.json";

    public void transformJSON() throws IOException {
        String jsonString = readFile(filename);
        Map<String, String> mapColorToSuperColors = new Gson().fromJson(jsonString, Map.class);
        this.setSuperColor(mapColorToSuperColors.get(this.color));
    }

    private String readFile(String pathname) throws IOException {
        File file = new File(pathname);
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");

        try {
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
}
