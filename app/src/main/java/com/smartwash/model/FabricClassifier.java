package com.smartwash.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabricClassifier {

    private final static List<FabricAttribute> classificationOrder = Arrays.asList(FabricAttribute.values());

    private final static ClassifierKey DEFAULT_TYPE_CLASSIFIER = new ClassifierKey("default", FabricAttribute.TYPE.getValue());
    private final static ClassifierKey DEFAULT_MATERIAL_CLASSIFIER = new ClassifierKey("default", FabricAttribute.MATERIAL.getValue());
    private final static ClassifierKey DEFAULT_COLOR_CLASSIFIER = new ClassifierKey("default", FabricAttribute.SUPER_COLOR.getValue());

    private final static List<String> fabricTypes = Arrays.asList("casual", "ethnic", "denim", "traditional", "formal", "sheets", "towels", "delicates", "default");
    private final static List<String> fabricMaterial = Arrays.asList("cotton", "satin", "polyester", "woolen", "blend", "rayon", "nylon", "default");
    private final static List<String> fabricColour = Arrays.asList("lavender", "purple", "pink", "magenta", "orange", "blue", "red", "green", "beige", "crimson"
            , "brown", "light brown", "yellow", "indigo", "blue", "violet", "yellow", "cream", "white", "black", "charcoal gray", "gray", "default");

    private Mapping mapping;

    private Map<ClassifierKey, Classifier> classifierMap;

    private static int bucketCount = 0;

    private static List<Fabric> fabricsProcessed = new ArrayList<>();

    public FabricClassifier() {
        this.mapping = new Mapping();
        this.classifierMap = generateClassifier();
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
                    classifier = classifierMap.get(new ClassifierKey(fabric.getType(), attribute.getValue()));
                    break;
                case MATERIAL:
                    if (classifier == null)
                        classifier = classifierMap.get(DEFAULT_TYPE_CLASSIFIER);
                    classifier = classifier.getClassifiers().get(new ClassifierKey(fabric.getMaterial(), attribute.getValue()));
                    break;
                case SUPER_COLOR:
                    if (classifier == null)
                        classifier = classifierMap.get(DEFAULT_MATERIAL_CLASSIFIER);
                    Classifier colorClassifier = classifier.getClassifiers().get(new ClassifierKey(fabric.getColor(), attribute.getValue()));
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
        Map<Integer, List<Fabric>> buckets = new HashMap<>();
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
