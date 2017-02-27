package com.predictive.analytics;

import java.io.File;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;

/**
 * Loads a dataset, builds Decision Tree on it and outputs the Classifier's
 * model.
 * 
 * @author Mohammed Abdul Aziz Syed
 * @email msyed6@leomail.tamuc.edu
 * @CWID 50143871
 * @Team Data Enthusiasts
 * @Members Mohammed Abdul Aziz Syed 50143871
 * 			Siva Prasad Bhushetty 	 50145947
 * 			Shoebuddin Ahmed Fnu 	 50144855
 * 			Paul Mandrumaka 		 50137144
 * @version $Revision: 25 $
 */
public class BuildLOLClassifier {
	
	private String source;
	private String model;
	// Load the arff file, which contains training data set
	ArffLoader trainLoader;
	
	public BuildLOLClassifier() {
		 trainLoader = new ArffLoader();
	}
	
	public void setSource(String source) {
		this.source = source;
	}
	
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Reads arff file and loads instances. These instances are fed to J48
	 * class, which uses C4.5 Algorithm to build classifier.
	 */
	public void j48Classifier() {
		try {
			trainLoader
					.setSource(new File(source));
			// Load/convert the data records as instances
			Instances data = trainLoader.getDataSet();
			// specify the index of attribute, which should be treated as class
			// and whose values are to be predicted on test data
			data.setClassIndex(4);

			// J48 Classifier
			// instantiate the J48 class. It uses C4.5 Algorithm to build
			// decision tree
			J48 classifier = new J48();
			// ensure that there is no pruning
			classifier.setUnpruned(true);
			// turn on debugging feature, so that we can debug in case of any
			// issues
			classifier.setDebug(true);
			// build the model for classification
			classifier.buildClassifier(data);
			// once the model is built, save it for applying it on test data set
			// in future because building model is a time consuming process
			SerializationHelper.write(model, classifier);

			System.out.println(classifier);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads arff file and loads instances. These instances are fed to J48
	 * class, which uses Naive Bayesian Algorithm to build classifier.
	 */
	public void naiveBayesClassifier() {
		try {
			// Naive Bayes Classifier
			trainLoader.setFile(new File(source));
			Instances structure = trainLoader.getStructure();
			// specifies the index of winner attribute in arff file
			structure.setClassIndex(28);
			// instantiate Naive Bayes classifier
			NaiveBayesUpdateable classifier = new NaiveBayesUpdateable();
			// build the classifier model
			classifier.buildClassifier(structure);
			Instance current = null;
			// get the model trained on every instance by iteratively updating
			while ((current = trainLoader.getNextInstance(structure)) != null)
				classifier.updateClassifier(current);
			// once the model is built, persist it for applying it on test data
			// set
			SerializationHelper.write(model, classifier);
			// print the summary of model
			System.out.println(classifier);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
