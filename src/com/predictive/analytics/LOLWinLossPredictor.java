package com.predictive.analytics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ArffLoader;
import weka.core.converters.Loader;

/**
 * Applies the learnt model on test data.
 *
 * @author  Mohammed Abdul Aziz Syed
 * @email   msyed6@leomail.tamuc.edu
 * @CWID    50143871
 * @Team    Data Enthusiasts
 * @Members Mohammed Abdul Aziz Syed 50143871
 *			Siva Prasad Bhushetty	 50145947
 *			Shoebuddin Ahmed Fnu	 50144855
 *			Paul Mandrumaka			 50137144
 * @version $Revision: 25 $
 */
public class LOLWinLossPredictor {

	private String testDataSet;
	private String predictedDataSet;
	private String classifierModel;
	
	public void setTestDataSet(String testDataSet) {
		this.testDataSet = testDataSet;
	}
	
	public void setPredictedDataSet(String predictedDataSet) {
		this.predictedDataSet = predictedDataSet;
	}
	
	public void setClassifierModel(String classifierModel) {
		this.classifierModel = classifierModel;
	}
	
	public void classifyWinLoss() {
		
		try {

			//count the error/exceptions, if any
			//during classification phase
			long errorCount = 0;
			
			//construct the ARFF Loader
			ArffLoader testLoader = new ArffLoader();
			//Set the test data set as source
			testLoader.setSource(new File(testDataSet));
			//make sure that we load the data in batches
			testLoader.setRetrieval(Loader.BATCH);
			//Create the weka instances out of the read records
			Instances testDataSet = testLoader.getDataSet();
			//string to be written out
			String output = "";

			//output file, where the predictions are written
			 BufferedWriter writer = new BufferedWriter(
						new FileWriter(predictedDataSet));

			//set the attribute index in test data i.e. winner attribute
			testDataSet.setClassIndex(28);

			//read/reactivate/rebuild the saved model/classifier
//			Classifier classifier = (Classifier) SerializationHelper
//					.read("J48_97Accurracy_25PercentData.model");
			Classifier classifier = (Classifier) SerializationHelper
					.read(classifierModel);
			
			// create copy of instances, to save the new/predicted value
			 Instances labeled = new Instances(testDataSet);
			 
			 // label instances
			 //iterate through all the test data instances
			 for (int i = 0; i < testDataSet.numInstances(); i++) {
				 try {
					 //find the class label index
					 double clsLabel = classifier.classifyInstance(testDataSet.instance(i));
					 //identify the underlying class
					 String classLabel = labeled.classAttribute().value((int)clsLabel);
					 //if the class is 'true', write 1 to the output
					 //because this is what our expection is, in terms
					 //of win/loss -> 1/0
					 if("true".equalsIgnoreCase(classLabel)) {
						 clsLabel = 1;
						 writer.write("1");
						 writer.newLine();
					 } else {
						 //if the class is 'false', write 0 to the output
						 //because this is what our expection is, in terms
						 //of win/loss -> 1/0
						 clsLabel = 0;
						 writer.write("0");
						 writer.newLine();
					 }
				 } catch(ArrayIndexOutOfBoundsException e) {
					 errorCount++;
//					 labeled.instance(i).setClassValue(1.0);
					 //print the stack trace, in case of any exceptions
					 e.printStackTrace();
				 }
			 }
			 // save labeled data
			 //flush the stream
			 writer.flush();
			 //close the stream
			 writer.close();
			 
			 //print the error records count
			 System.out.println("Error in " + errorCount + " classified instances!!");

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
