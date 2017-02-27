package com.predictive.analytics;

/**
 * Starter program for the data classification.
 * Performs pre-processing, data mining and 
 * then post-processing.
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
public class LOLDataClassifier {

	public static void main(String[] args) {
		
		//Pre Processing Step
		TrainingDataRefinerCSV2Arff trainingDataSet = new TrainingDataRefinerCSV2Arff();
		trainingDataSet.setTrainingCSV("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\training_set\\training_set_4_1.csv");
		trainingDataSet.setTrainingARFF("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\training_set\\training_set_1_4_4.arff");
		trainingDataSet.preProcess();
		
		//Data Mining Step
		BuildLOLClassifier lolClassifier = new BuildLOLClassifier();
		lolClassifier.setSource("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\training_set\\game.arff");
		lolClassifier.setModel("game_lol_pca_1.model");
		lolClassifier.j48Classifier();
		
		//Pre Process Test Data Set Step
		PreprocessTestData testData = new PreprocessTestData();
		testData.setCrudeTestSet("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\test_set_refined.arff");
		testData.setRefinedTestSet("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\test_set_refined_j48.arff");
		testData.preProcess();
		
		//Prediction/Classification Step
		LOLWinLossPredictor lolWinLossPredictor = new LOLWinLossPredictor();
		lolWinLossPredictor.setTestDataSet("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\test_set_refined_j48_2.arff");
		lolWinLossPredictor.setPredictedDataSet("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\test_set_refined_j48_Random_Records_2.arff");
		lolWinLossPredictor.setClassifierModel("J48_97_Percent_Random_Records.model");
		lolWinLossPredictor.classifyWinLoss();
		
		//Post Processing Step
		//Kaggle File Formatter
		KaggleOutputFileWriter kaggleOutputFileWriter = new KaggleOutputFileWriter();
		kaggleOutputFileWriter.setRefFile("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\DataEnthusiasts_1.arff");
		kaggleOutputFileWriter.setPredictedFile("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\test_set_predicted_jugaad.arff");
		kaggleOutputFileWriter.setOutputFile("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\DataEnthusiasts_Jugaad_3.arff");
		kaggleOutputFileWriter.prepareForSubmission();

	}

}
