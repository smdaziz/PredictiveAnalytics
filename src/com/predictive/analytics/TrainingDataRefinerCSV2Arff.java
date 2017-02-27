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
public class TrainingDataRefinerCSV2Arff {
	
	private String trainingCSV;
	private String trainingARFF;
	
	public void setTrainingCSV(String trainingCSV) {
		this.trainingCSV = trainingCSV;
	}
	
	public void setTrainingARFF(String trainingARFF) {
		this.trainingARFF = trainingARFF;
	}

	public void preProcess() {

		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(trainingCSV)));
			bufferedWriter = new BufferedWriter(new FileWriter(new File(trainingARFF)));
			String input = null;
			while((input=bufferedReader.readLine()) != null) {
				String inputTokens[] = input.split(",");
				String output = "";
				for(int i = 1; i < inputTokens.length-1; i++) {
					if(i != 36 && i != 92) {
						output += (inputTokens[i].length()>0) ? inputTokens[i]+"," : "?,";
					}
				}
				output += inputTokens[inputTokens.length-1] + "\n";
				bufferedWriter.write(output);
				bufferedWriter.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedReader.close();
				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
