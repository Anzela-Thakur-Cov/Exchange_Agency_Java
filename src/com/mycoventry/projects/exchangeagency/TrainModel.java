package com.mycoventry.projects.exchangeagency;

import java.io.File;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;

public class TrainModel {
    public static void main(String[] args) throws Exception {
        // Define the path for the ARFF file
        String arffFilePath = "C:\\Users\\anzel\\Desktop\\MLWeka\\model.arff";

        // Load the training data
        ArffLoader loader = new ArffLoader();
        loader.setFile(new File(arffFilePath));
        Instances data = loader.getDataSet();

        // Set the class index to the last attribute (category)
        data.setClassIndex(data.numAttributes() - 1);

        // Train the model
        Classifier model = new weka.classifiers.trees.J48();  // Using J48 classifier
        model.buildClassifier(data);

        // Define the path for the model file
        String modelFilePath = "C:\\Users\\anzel\\Desktop\\MLWeka\\model.model";

        // Save the model
        SerializationHelper.write(modelFilePath, model);

        System.out.println("Model training complete and saved to " + modelFilePath);
    }
}
