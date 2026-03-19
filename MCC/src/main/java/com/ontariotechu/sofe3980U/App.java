package com.ontariotechu.sofe3980U;


import java.io.FileReader; 
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * Evaluate Single Variable Continuous Regression
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		String filePath="model.csv";
		FileReader filereader;
		List<String[]> allData;
		try{
			filereader = new FileReader(filePath); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
		}
		catch(Exception e){
			System.out.println( "Error reading the CSV file" );
			return;
		}

		int[][] matrix = new int[5][5];


		double ce = 0.0;

		for(String[] row : allData) {

			int trueClass = Integer.parseInt(row[0]);
			int trueIdx = trueClass - 1;

			double maxProb = -1;
			int predIdx = -1;

			for(int i = 1; i <= 5; i++) {
				double p  = Double.parseDouble(row[i]);
				
				if(p > maxProb) {
					maxProb = p;
					predIdx = i - 1;
				}
			}

			matrix[predIdx][trueIdx]++;

			ce += Math.log(Double.parseDouble(row[trueClass]));


		}

		

		System.out.println("CE =" + -ce/allData.size());
		System.out.println("Confusion matrix");

		System.out.println("\ty=1\ty=2\ty=3\ty=4\ty=5");
		for(int i = 0; i < 5; i++) {
			System.out.print("y=" + (i+1) + "\t");
			for(int j = 0; j < 5; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.println();
		}
		
		

	}
	
}
