package com.mycoventry.projects.exchangeagency;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.classifiers.Classifier;
import weka.core.converters.ArffLoader;

import java.io.File;

public class ProductCategoryPredictor {
    private Classifier model;
    private Instances dataStructure;

    // No-argument constructor
    public ProductCategoryPredictor() throws Exception {
        // Load the model from file
        model = (Classifier) SerializationHelper.read("C:\\Users\\anzel\\Desktop\\MLWeka\\model.model");
        // Load the structure of the dataset from ARFF file
        dataStructure = loadDataStructure("C:\\Users\\anzel\\Desktop\\MLWeka\\model.arff");
    }

    public String predictCategory(String name, String brand, String features) throws Exception {
        // Prepare the instance to classify
        Instance instanceToPredict = createInstance(name, brand, features);
        // Perform classification
        double prediction = model.classifyInstance(instanceToPredict);
        return dataStructure.classAttribute().value((int) prediction);
    }

    private Instances loadDataStructure(String dataFilePath) throws Exception {
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(dataFilePath));
        Instances data = loader.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);
        return data;
    }

    private Instance createInstance(String name, String brand, String features) {
        // Create a new instance with the same structure as the loaded dataset
        Instance newInstance = new DenseInstance(dataStructure.numAttributes());
        newInstance.setDataset(dataStructure);
        newInstance.setValue(dataStructure.attribute("name"), name);
        newInstance.setValue(dataStructure.attribute("brand"), brand);
        newInstance.setValue(dataStructure.attribute("features"), features);
        return newInstance;
    }
}
