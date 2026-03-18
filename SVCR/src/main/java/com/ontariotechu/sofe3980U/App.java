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

	public static List<String[]> readCSV(String filePath) {
		FileReader filereader;
		List<String[]> allData;
		try{
			filereader = new FileReader(filePath); 
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build(); 
			allData = csvReader.readAll();
			return allData;
		}
		catch(Exception e){
			System.out.println( "Error reading the CSV file" );
			return null;
		}
	}
    public static void main( String[] args )
    {
		Model model1 = new Model("model_1.csv", readCSV("model_1.csv"));
		Model model2 = new Model("model_2.csv", readCSV("model_2.csv"));
		Model model3 = new Model("model_3.csv", readCSV("model_3.csv"));

		model1.printStats();
		model2.printStats();
		model3.printStats();

		minMSE(model1, model2, model3);
		minMAE(model1, model2, model3);
		minMARE(model1, model2, model3);
		
		
		
		
    }

	public static void minMSE(Model ... models) {

		if (models.length == 0) return;

		Model min = models[0];
		double minMSEVal = min.calcMSE();

		for(int i = 1; i < models.length; i++) {
			double mseVal = models[i].calcMSE();
			if (mseVal < minMSEVal) {
				minMSEVal = mseVal;
				min = models[i];
			}
		}

		System.out.println("According to MSE, The best model is "+ min.getFilePath());
	}

	public static void minMAE(Model ... models) {
		if (models.length == 0) return;

		Model min = models[0];
		double minMAEVal = min.calcMAE();

		for(int i = 1; i < models.length; i++) {
			double maeVal = models[i].calcMAE();
			if (maeVal < minMAEVal) {
				minMAEVal = maeVal;
				min = models[i];
			}
		}

		System.out.println("According to MAE, The best model is "+ min.getFilePath());
	}

	public static void minMARE(Model ... models) {
		if (models.length == 0) return;

		Model min = models[0];
		double minMAREVal = min.calcMARE();

		for(int i = 1; i < models.length; i++) {
			double mareVal = models[i].calcMARE();
			if (mareVal < minMAREVal) {
				minMAREVal = mareVal;
				min = models[i];
			}
		}

		System.out.println("According to MARE, The best model is "+ min.getFilePath());
	}
}
