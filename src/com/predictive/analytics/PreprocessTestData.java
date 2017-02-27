package com.predictive.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Preprocesses test data and by selecting
 * only those attributes that were included
 * in training data set, in the same order.
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
public class PreprocessTestData {
	
	private String crudeTestSet;
	private String refinedTestSet;
	
	public void setCrudeTestSet(String crudeTestSet) {
		this.crudeTestSet = crudeTestSet;
	}
	
	public void setRefinedTestSet(String refinedTestSet) {
		this.refinedTestSet = refinedTestSet;
	}

	public void preProcess() {

		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(new File(crudeTestSet)));
			bufferedWriter = new BufferedWriter(new FileWriter(new File(refinedTestSet)));
			String input = null;
			while((input=bufferedReader.readLine()) != null) {
				String inputTokens[] = input.split(",");
				String output = "";
				for(int i = 0; i < inputTokens.length; i++) {
					if(i != 33 && i != 88) {
						if(i == 103)
							output += "?";
						else
							output += (inputTokens[i].length()>0) ? inputTokens[i]+"," : "?,";
					}
				}
				output += "\n";
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
