package com.smartwash.model;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabricClassifier {

    private final static List<FabricAttribute> classificationOrder = Arrays.asList(FabricAttribute.values());

    private final static ClassifierKey DEFAULT_TYPE_CLASSIFIER = new ClassifierKey("default", "Type");
    private final static ClassifierKey DEFAULT_MATERIAL_CLASSIFIER = new ClassifierKey("default",  "Material");
    private final static ClassifierKey DEFAULT_COLOR_CLASSIFIER = new ClassifierKey("default", "SuperColor");

    private final static List<String> fabricTypes = Arrays.asList("casual", "ethnic", "denim", "traditional", "formal", "sheets", "towels", "delicates");
    private final static List<String> fabricMaterial = Arrays.asList("cotton", "satin", "polyester", "woolen", "blend", "rayon", "nylon");
    private final static List<String> fabricColour = Arrays.asList("lavender", "purple", "pink", "magenta", "orange", "blue", "red", "green", "beige", "crimson"
            , "brown", "light brown", "yellow", "indigo", "blue", "violet", "yellow", "cream", "white", "black", "charcoal gray", "gray");

    private static Map<ClassifierKey, Classifier> generateClassifier() {
        Map<ClassifierKey, Classifier>  classifierMap = new HashMap<>();
        for (String type: fabricTypes) {
            ClassifierKey classifierKey = new ClassifierKey( type, "Type");
            Classifier typeClassifier = new Classifier();
            typeClassifier.setClassifierKey(classifierKey);
            classifierMap.put(classifierKey, typeClassifier);

            Map<ClassifierKey, Classifier> materialClassifierMap = new HashMap<>();
            typeClassifier.setClassifiers(materialClassifierMap);

            for (String material: fabricMaterial){
                ClassifierKey materialClassifierKey = new ClassifierKey(material, "Material");
                Classifier materialClassifier = new Classifier();
                materialClassifier.setClassifierKey(materialClassifierKey);
                materialClassifierMap.put(materialClassifierKey, materialClassifier);

                Map<ClassifierKey, Classifier> colourClassifierMap = new HashMap<>();
                materialClassifier.setClassifiers(colourClassifierMap);

                for(String color: fabricColour){

                    // get super color
                    ClassifierKey colorClassifierKey = new ClassifierKey(color, "SuperColor");
                    Classifier colourClassifier = new Classifier();
                    colourClassifier.setClassifierKey(colorClassifierKey);
                    colourClassifierMap.put(colorClassifierKey, colourClassifier);

                }
            }
        }
        return classifierMap;
    }
}
