package com.predictive.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Refines training data set and 
 * creates .arff file out of it.
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
public class KaggleOutputFileWriter {
	
	private String refFile;
	private String predictedFile;
	private String outputFile;
	
	public void setRefFile(String refFile) {
		this.refFile = refFile;
	}
	
	public void setPredictedFile(String predictedFile) {
		this.predictedFile = predictedFile;
	}
	
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public void prepareForSubmission() {

		BufferedReader bufferedReaderTestSet = null;
		BufferedReader bufferedReaderPredictedSet = null;
		BufferedWriter bufferedWriter = null;
		long goodRecordCount = 0;
		try {
//			bufferedReaderTestSet = new BufferedReader(new FileReader(new File("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\test_set_refined.arff")));
//			bufferedReaderPredictedSet = new BufferedReader(new FileReader(new File("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\test_set_predicted.arff")));
//			bufferedWriter = new BufferedWriter(new FileWriter(new File("F:\\MS\\TAMUC\\Fall-2015\\DataMining\\Assignment\\test_set\\DataEnthusiasts_1.arff")));
			bufferedReaderTestSet = new BufferedReader(new FileReader(new File(refFile)));
			bufferedReaderPredictedSet = new BufferedReader(new FileReader(new File(predictedFile)));
			bufferedWriter = new BufferedWriter(new FileWriter(new File(outputFile)));
			String inputTestSet = null;
			String inputPredictedSet = null;
			while((inputTestSet = bufferedReaderTestSet.readLine()) != null && (inputPredictedSet = bufferedReaderPredictedSet.readLine()) != null) {
				String inputTokensTestSet[] = inputTestSet.split(",");
				String inputTokensPredictedSet[] = inputPredictedSet.split(",");
				String output = "";
				/*for(int i = 0; i < inputTokens.length-1; i++) {
						output += (inputTokens[i].length()>0) ? inputTokens[i]+"," : "?,";
				}
				output += inputTokens[inputTokens.length-1] + ",false\n";*/
				//count good records
				/*boolean recordGood = true;
				for(int i = 1, j = 0; i < inputTokensTestSet.length - 2 && j < inputTokensPredictedSet.length - 2; i++, j++) {
					if(!inputTokensTestSet[i].equalsIgnoreCase(inputTokensPredictedSet[j])) {
						recordGood = false;
						break;
					}
				}
				if(recordGood)
					goodRecordCount++;*/
				/*String winLoss = "1";
				if("false".equalsIgnoreCase(inputTokensPredictedSet[inputTokensPredictedSet.length - 1]))
					winLoss = "0";
				output = inputTokensTestSet[0] + "," + winLoss + "\n";*/
				output = inputTokensTestSet[0] + "," + inputTokensPredictedSet[0] + "\n";
				bufferedWriter.write(output);
				bufferedWriter.flush();
			}
			System.out.println("Good Records : " + goodRecordCount);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReaderTestSet.close();
				bufferedReaderPredictedSet.close();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
