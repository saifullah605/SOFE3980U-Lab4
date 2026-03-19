package com.ontariotechu.sofe3980U;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private String filePath;
    private List<String[]> allData;
    
    private double accuracy;
    public double getAccuracy(){
        return accuracy;
    }
    private double precision;
    public double getPrecision() {
        return precision;
    }


    private double recall;
    public double getRecall() {
        return recall;
    }
    private double f1; 
    public double getF1() {
        return f1;
    } 
    private double auc;
    public double getAUC() {
        return auc;
    }

    private double bce;
    public double bce() {
        return bce;
    }


    public Model(String filePath, List<String[]> allData) {
        this.filePath = filePath;
        this.allData = allData;
    }

    public String getFilePath() {
        return filePath;
    }

    public double calcBCE() {
        bce = 0.0;
        int n = allData.size();

        for (String[] row : allData) {
            int y_true = Integer.parseInt(row[0]);
            double y_predicted = Double.parseDouble(row[1]);
            bce += y_true == 1 ? Math.log(y_predicted) : Math.log(1-y_predicted);
        }

        
        bce = -bce/n;
        return bce;
    }

    public List<double[]> ROC() {
        int numPos = 0;
        int numNeg = 0;

        List<double[]> points = new ArrayList<>();    

        for(String[] row: allData) {
            float y_true = Float.parseFloat(row[0]);
            if (y_true == 1) {
                numPos++;
            } else {
                numNeg++;
            }
        }

        for(int i = 0; i <=100; i ++) {
            double threshold = i / 100.0;
            int TP = 0;
            int FP = 0;

            for(String[] row: allData) {
                float y_true = Float.parseFloat(row[0]);
                float y_predicted = Float.parseFloat(row[1]);
                if (y_predicted >= threshold) {
                    if (y_true == 1) {
                        TP++;
                    } else {
                        FP++;
                    }
                }
            }

            double TPR = (double) TP / numPos;
            double FPR = (double) FP / numNeg;

            points.add(new double[]{FPR, TPR});
        }

        return points;
    }

    public double calcAuc() {
        List<double[]> points = ROC();
        auc = 0.0;

        for(int i = 1; i < points.size(); i++) {
            double x1 = points.get(i-1)[0];
            double y1 = points.get(i-1)[1];
            double x2 = points.get(i)[0];
            double y2 = points.get(i)[1];

            auc += Math.abs(x2-x1) * (y1 + y2) / 2;
        }

        return auc;
    }


    public void calcMetrics() {
        int TP = 0;
        int FP = 0;
        int TN = 0;
        int FN = 0;
    
        for(String[] row: allData) {
            float y_true = Float.parseFloat(row[0]);
            float y_predicted = Float.parseFloat(row[1]);
            if (y_predicted >= 0.5) {
                if (y_true == 1) {
                    TP++;
                } else {
                    FP++;
                }
            } else {
                if (y_true == 1) {
                    FN++;
                } else {
                    TN++;
                }
            }
        }
    
        accuracy = (double)(TP + TN) / allData.size();
        precision = TP + FP == 0 ? 0 : (double) TP / (TP + FP);
        recall = TP + FN == 0 ? 0 : (double) TP / (TP + FN);
        f1 = precision + recall == 0 ? 0 : 2 * (precision * recall) / (precision + recall);

        System.out.println("\tConfusion matrix");
        System.out.println("\t\t\ty=1\ty=0");
        System.out.println("\t\ty^1=1\t" + TP + "\t"+ FP);
        System.out.println("\t\ty^1=0\t" + FN + "\t"+ TN);
    }

    public void printStats() {
        calcBCE();
        calcAuc();
        
        System.out.println("for " + filePath);
        System.out.println("\tBCE =" + bce);
        calcMetrics();
        System.out.println("\tAccuracy =" + accuracy);
        System.out.println("\tPrecision =" + precision);
        System.out.println("\tRecall =" + recall);
        System.out.println("\tf1 score =" + f1);
        System.out.println("\tauc roc =" + auc);
    }

    



}
