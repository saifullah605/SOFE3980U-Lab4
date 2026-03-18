package com.ontariotechu.sofe3980U;
import java.util.List;

public class Model {

    private String filePath;
    private List<String[]> allData;
    

    public Model(String filePath, List<String[]> allData) {
        this.filePath = filePath;
        this.allData = allData;
    }

    public String getFilePath() {
        return filePath;
    }

    public double calcMSE() {
        double mse = 0.0;
        int n = allData.size();

        for (String[] row : allData) {
            float y_true = Float.parseFloat(row[0]);
            float y_predicted = Float.parseFloat(row[1]);
            mse += Math.pow(y_true - y_predicted, 2);
        }

        return mse / n;
    }

    public double calcMAE() {
        double mae = 0.0;
        int n = allData.size();

        for (String[] row : allData) {
            float y_true = Float.parseFloat(row[0]);
            float y_predicted = Float.parseFloat(row[1]);
            mae += Math.abs(y_true - y_predicted);
        }

        return mae / n;
    }

    public double calcMARE() {
        double mare = 0.0;
        int n = allData.size();

        for (String[] row : allData) {
            float y_true = Float.parseFloat(row[0]);
            float y_predicted = Float.parseFloat(row[1]);
            mare += Math.abs(y_true - y_predicted) / Math.abs(y_true);
        }

        return mare / n;
    }

    public void printStats() {
        System.out.println("for " + filePath);
        System.out.println("\tMSE = " + calcMSE());
        System.out.println("\tMAE = " + calcMAE());
        System.out.println("\tMARE = " + calcMARE());
    }



}
