package knn;

import java.io.FileNotFoundException;

//you can change k in KNNAlgorithm class to increase number of neighbours
//you can increase the number of samples for training in trainingSet class. under this class, their is another class called processTrainSamples. change numRows variable to a higher number or lower.
//you can increase the number of samples for testing in testSet class. under this class, their is another class called processTestSamples. change numRows variable to a higher number or lower.


public class MainClass {

    public static void main(String[] args) throws FileNotFoundException  {

        CrossValidation cv = new CrossValidation();
        processTestSamples testset = new processTestSamples();
        processTrainSamples trainset = new processTrainSamples();


        int folds = 5;




        System.out.println("*****************************************************K-NN*****************************************************");

        cv.knnModel(trainset.trainSet(), testset.testingSet(), cv.k);

        System.out.println("*************************************************"+folds+"-Cross Validation"+"*************************************************");
        for(int i = 0; i < folds;i++) {
            cv.crossValidation(trainset.trainSet(), testset.testingSet(), 5, i);   //iterate cross validation 5 times

        }
    }



}