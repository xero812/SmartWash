package com.smartwash.model;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.io.FileDescriptor;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FabricClassifier implements Serializable{

    private static final long serialVersionUID = 1L;
    private final static List<FabricAttribute> classificationOrder = Arrays.asList(FabricAttribute.values());

    private final static ClassifierKey DEFAULT_TYPE_CLASSIFIER = new ClassifierKey("default", FabricAttribute.TYPE.getValue());
    private final static ClassifierKey DEFAULT_MATERIAL_CLASSIFIER = new ClassifierKey("default", FabricAttribute.MATERIAL.getValue());
    private final static ClassifierKey DEFAULT_COLOR_CLASSIFIER = new ClassifierKey("default", FabricAttribute.SUPER_COLOR.getValue());

    private final static List<String> fabricTypes = Arrays.asList("tshirt", "top", "kurta", "blazzer", "trousers", "jeans", "sheets", "towels", "delicates", "default");
    private final static List<String> fabricMaterial = Arrays.asList("cotton", "satin", "polyester", "woolen", "blend", "rayon", "nylon", "denim", "default");
    private final static List<String> fabricColour = Arrays.asList("lavender", "purple", "pink", "magenta", "orange", "blue", "red", "green", "beige", "crimson"
            , "brown", "light brown", "yellow", "indigo", "blue", "violet", "yellow", "cream", "white", "black", "charcoal gray", "gray", "default");
    private final static Map<String, String> childTypeToType= new HashMap<>();
    private Mapping mapping;

    private Map<ClassifierKey, Classifier> classifierMap;

    private static int bucketCount = 0;

    private static List<Fabric> fabricsProcessed = new ArrayList<>();

    public FabricClassifier() {
        setChildTypeToType();
    }

    public FabricClassifier(InputStream file) {
        setChildTypeToType();
        this.mapping = new Mapping();
        mapping.transformJSON(file);
        this.classifierMap = generateClassifier();
    }

    private void setChildTypeToType(){
        childTypeToType.put("tshirt","top");
        childTypeToType.put("top","top");
        childTypeToType.put("kurta","kurta");
        childTypeToType.put("blazzer","blazzer");
        childTypeToType.put("trousers","trousers");
        childTypeToType.put("jeans","trousers");
        childTypeToType.put("sheets","sheets");
        childTypeToType.put("towels","towels");
        childTypeToType.put("delicates","delicates");
        childTypeToType.put("default","default");
    }

    private Map<ClassifierKey, Classifier> generateClassifier() {
        Map<ClassifierKey, Classifier> classifierMap = new HashMap<>();
        for (String type : fabricTypes) {
            ClassifierKey classifierKey = new ClassifierKey(type, FabricAttribute.TYPE.getValue());
            Classifier typeClassifier = new Classifier();
            typeClassifier.setClassifierKey(classifierKey);
            classifierMap.put(classifierKey, typeClassifier);

            Map<ClassifierKey, Classifier> materialClassifierMap = new HashMap<>();
            typeClassifier.setClassifiers(materialClassifierMap);

            for (String material : fabricMaterial) {
                ClassifierKey materialClassifierKey = new ClassifierKey(material, FabricAttribute.MATERIAL.getValue());
                Classifier materialClassifier = new Classifier();
                materialClassifier.setClassifierKey(materialClassifierKey);
                materialClassifierMap.put(materialClassifierKey, materialClassifier);

                Map<ClassifierKey, Classifier> colourClassifierMap = new HashMap<>();
                materialClassifier.setClassifiers(colourClassifierMap);

                for (String color : fabricColour) {
                    ClassifierKey colorClassifierKey = new ClassifierKey(mapping.getColorSuperColorMap().get(color), FabricAttribute.SUPER_COLOR.getValue());
                    Classifier colourClassifier = new Classifier();
                    colourClassifier.setClassifierKey(colorClassifierKey);
                    colourClassifierMap.put(colorClassifierKey, colourClassifier);
                }
            }
        }
        return classifierMap;
    }

    public int slotBucket(Fabric fabric) {
        int resultedBucketNo;
        Classifier classifier = null;
        for (FabricAttribute attribute : classificationOrder) {
            switch (attribute) {
                case TYPE:
                    classifier = classifierMap.get(new ClassifierKey(childTypeToType.get(fabric.getType()), attribute.getValue()));
                    break;
                case MATERIAL:
                    if (classifier == null)
                        classifier = classifierMap.get(DEFAULT_TYPE_CLASSIFIER);
                    classifier = classifier.getClassifiers().get(new ClassifierKey(fabric.getMaterial(), attribute.getValue()));
                    break;
                case SUPER_COLOR:
                    if (classifier == null)
                        classifier = classifierMap.get(DEFAULT_MATERIAL_CLASSIFIER);
                    Classifier colorClassifier = classifier.getClassifiers().get(new ClassifierKey(mapping.getColorSuperColorMap().get(fabric.getColor()), attribute.getValue()));
                    classifier = colorClassifier == null ? classifier : colorClassifier;
                    break;
            }
        }
        if (classifier != null && classifier.getBucketNo() == null) {
            classifier.setBucketNo(++bucketCount);
        }
        fabric.setBucketId(classifier.getBucketNo());
        fabricsProcessed.add(fabric);
        return classifier.getBucketNo();
    }


    public List<Bucket> getBuckets() {
        List<Bucket> result = new ArrayList<>();
        TreeMap<Integer, List<Fabric>> buckets = new TreeMap<>();
        for (Fabric fabric : fabricsProcessed) {
            if (buckets.get(fabric.getBucketId()) == null) {
                buckets.put(fabric.getBucketId(), new ArrayList<Fabric>());
            }
            buckets.get(fabric.getBucketId()).add(fabric);
        }
        for (Integer id : buckets.keySet()) {
            result.add(new Bucket(id, buckets.get(id)));
        }
        return result;
    }
}
