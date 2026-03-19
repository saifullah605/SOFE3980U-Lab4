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

		Model[] models = {model1, model2, model3};

		for(Model model : models) {
			model.printStats();
		}

		minBCE(models);
		MaxAccuracy(models);
		MaxPrecision(models);
		MaxRecall(models);
		MaxF1(models);
		MaxAoc(models);
		



    }


	public static void minBCE(Model[] models) {
		if (models.length == 0) return;
		Model minModel = models[0];

		for(int i = 1; i < models.length; i ++) {
			if(models[i].calcBCE() < minModel.calcBCE()) {
				minModel = models[i];
			}
		}
		
		System.out.println("According to BCE, The best model is " + minModel.getFilePath()); 
	}

	public static void MaxAccuracy(Model[] models) {
		if (models.length == 0) return;
		Model maxModel = models[0];

		for(int i = 1; i < models.length; i ++) {
			if(models[i].getAccuracy() > maxModel.getAccuracy()) {
				maxModel = models[i];
			}
		}
		
		System.out.println("According to Accuracy, The best model is " + maxModel.getFilePath());
	}

	public static void MaxPrecision(Model[] models) {
		if (models.length == 0) return;
		Model maxModel = models[0];

		for(int i = 1; i < models.length; i ++) {
			if(models[i].getPrecision() > maxModel.getPrecision()) {
				maxModel = models[i];
			}
		}
		
		System.out.println("According to Precision, The best model is " + maxModel.getFilePath());
	}

	public static void MaxRecall(Model[] models) {
		if (models.length == 0) return;
		Model maxModel = models[0];

		for(int i = 1; i < models.length; i ++) {
			if(models[i].getRecall() > maxModel.getRecall()) {
				maxModel = models[i];
			}
		}
		
		System.out.println("According to Recall, The best model is " + maxModel.getFilePath());
	}

	public static void MaxF1(Model[] models) {
		if (models.length == 0) return;
		Model maxModel = models[0];

		for(int i = 1; i < models.length; i ++) {
			if(models[i].getF1() > maxModel.getF1()) {
				maxModel = models[i];
			}
		}
		
		System.out.println("According to F1, The best model is " + maxModel.getFilePath());


	}

	public static void MaxAoc(Model[] models) {
		if (models.length == 0) return;
		Model maxModel = models[0];

		for(int i = 1; i < models.length; i ++) {
			if(models[i].getAUC() > maxModel.getAUC()) {
				maxModel = models[i];
			}
		}
		
		System.out.println("According to AUC ROC, The best model is " + maxModel.getFilePath());
	}


	

	
}
