package knn;

import java.util.*;
import java.io.FileNotFoundException;


public class CrossValidation extends KNNAlgorithm {

    int classcounter1 = 0;
    int classcounter2 = 0;

    double[] logAllFoldAccuracies = new double[5];



    public int[][][] crossValidation(trainingSet[][] trainset,testSet[][] testset,int folds, int s) throws FileNotFoundException {
        int length = trainset.length;
        classcounter1 = 0;
        int[][][] crossval = new int[folds][length/folds][14];
        double acc = 0;
        double[] acc_each_fold = new double[5];
        String[] prediction = new String[length/folds];
        String[] outcome = new String[length/folds];
        int count2 = 0;
        int count8 = 0;

        for(int i = s; i < s+ 1;i++) {
            for(int j = s*length/folds; j < testset.length;j++) {
                if(count2 < length/folds && count8 <= k) {
                    if(count8 == k && count2 < length/folds) {
                        count8 = 0;
                    }
                    crossval[i][count2] = new processTestSamples().twodRowsTest(testset)[j];
                    String resp = testset[(int) neighbours[count8]][0].getEarnsTest();
                    String resp2 = testset[j][0].getEarnsTest();
                    prediction[count2] = resp;
                    outcome[count2] = resp2;
                    if(outcome[0].equals(">50K") && count2 == length/folds-1) {
                        for(int o = 1; o < outcome.length;o++) {
                            if(outcome[o].equals("<=50K")) {
                                String outcome0 = outcome[0];
                                String prediction0 = prediction[0];
                                outcome[0] = outcome[o];
                                prediction[0] = prediction[o];
                                outcome[o] = outcome0;
                                prediction[o] = prediction0;
                                break;
                            }
                        }
                    }
                    count2++;
                    count8++;
                }
            }
        }


        int counter3 = 2;
        java.util.List<String> Category = Arrays.asList(outcome);
        int[][] confusionMatrix = new int[counter3][counter3];
        int b = 0;                                                     //compute confusion matrix to find accuracy for test set
        for(String instance : prediction) {
            String oL = instance;
            String aL = outcome[b];
            int oLI = Category.indexOf(oL);
            int aLI = Category.indexOf(aL);

            if(aLI >= counter3 && oLI < counter3 ) {
                confusionMatrix[1][oLI] += 1;
            }
            if(oLI >= counter3 && aLI < counter3) {

                confusionMatrix[aLI][1] += 1;
            }

            if(oLI >= counter3 && aLI >= counter3 ) {
                confusionMatrix[1][1] += 1;
            }

            if(aLI < counter3 && oLI < counter3) {
                confusionMatrix[aLI][oLI] += 1;

            }


            b++;
        }
        acc = confusionMatrix[0][0] + confusionMatrix[1][1];
        acc = acc/(length/folds);
        acc = Math.round(acc * 100.0)/100.0;
        acc_each_fold[classcounter1] = acc;
        classcounter1++;

        int count = 0;
        int count4 = 0;
        int count9 = 0;
        String[] tempoutcome = new String[length];
        String[] tempprediction = new String[length];
        int[] temparr = new int[length];

        for(int i = 0; i < folds;i++) {
            count = 0;
            for(int j = 0; j < length;j++) {
                if(i != s  && new processTrainSamples().foldArr()[j] == i && count < length/folds && count9 <= k) {

                    if(count9 == k && count < length/folds) {
                        count9 = 0;
                    }
                    if(count == 0) {
                        count9 = 0;
                    }

                    crossval[i][count] = new processTrainSamples().twodRowsTrain(trainset)[j];
                    temparr[count4] =j;

                    String resp = trainset[(int) neighbours[count9]][0].getEarns();
                    String resp2 = trainset[j][0].getEarns();
                    tempprediction[count4] = resp;
                    tempoutcome[count4] = resp2;
                    count++;
                    count4++;
                    count9++;
                }
            }
        }
        String[] tempoutcome2 = new String[count4];
        String[] tempprediction2 = new String[count4];
        int[] temparr2 = new int[count4];

        int[] indexes = new int[count4];
        int[] indexes2 = new int[folds];

        int count3 = 0;
        System.arraycopy(tempoutcome, 0, tempoutcome2, 0, count4);
        System.arraycopy(tempprediction, 0, tempprediction2, 0, count4);
        System.arraycopy(temparr, 0, temparr2, 0, count4);

        for(int i = 0;i < indexes.length-1;i++) {
            if(temparr2[i] > temparr2[i+1]) {
                indexes[count3] = i;
                count3++;
            }
        }
        indexes[folds-2] = count4-1;
        indexes2[0] = -1;
        System.arraycopy(indexes, 0, indexes2, 1, folds-1);
        int count5 = 0;
        int count6 = 0;
        int count7 = 0;
        int count10 = 0;
        for(int i = 0;i < indexes2.length- 1;i++) {
            count5 = 0;
            for(int j = indexes2[i]+1; j <= indexes2[i+1];j++) {

                if(count10 == k && count5 < length/folds) {
                    count10 = 0;
                }
                if(count5 == 0) {
                    count10 = 0;
                }


                String[] tempoutcomearray = new String[count5+1];
                String[] temppredictionarray = new String[count5+1];
                count6 = temparr[j];
                String resp = trainset[(int) neighbours[count10]][0].getEarns();
                String resp2 = trainset[count6][0].getEarns();
                prediction[count5] = resp;
                outcome[count5] = resp2;

                if(outcome[0].equals(">50K") && count5 == indexes2[i+1] - indexes2[i]-1) {
                    for(int o = 1; o < outcome.length;o++) {
                        if(outcome[o].equals("<=50K")) {
                            String outcome0 = outcome[0];
                            String prediction0 = prediction[0];
                            outcome[0] = outcome[o];
                            prediction[0] = prediction[o];
                            outcome[o] = outcome0;
                            prediction[o] = prediction0;
                            break;
                        }
                    }
                }
                if(j == indexes2[i] && count5 < length/folds - 1) {

                    System.arraycopy(outcome, 0, tempoutcomearray, 0, tempoutcomearray.length);
                    System.arraycopy(prediction, 0, temppredictionarray, 0, temppredictionarray.length);
                }

                if(count7 == indexes2[i+1]) {
                    if(j == indexes2[i] && count5 < length/folds - 1) {
                        counter3 = 2;
                        Category = Arrays.asList(tempoutcomearray);
                        confusionMatrix = new int[counter3][counter3];
                        b = 0;
                        for(String instance : temppredictionarray) {
                            String oL = instance;
                            String aL = tempoutcomearray[b];

                            int oLI = Category.indexOf(oL);

                            int aLI = Category.indexOf(aL);

                            if(aLI >= counter3 && oLI < counter3 ) {
                                confusionMatrix[1][oLI] += 1;
                            }
                            if(oLI >= counter3 && aLI < counter3) {

                                confusionMatrix[aLI][1] += 1;
                            }

                            if(oLI >= counter3 && aLI >= counter3 ) {
                                confusionMatrix[1][1] += 1;
                            }

                            if(aLI < counter3 && oLI < counter3 && aLI > -1 && oLI > -1) {
                                confusionMatrix[aLI][oLI] += 1;

                            }
                            if(oLI == -1) {
                                confusionMatrix[aLI][1] += 1;

                            }

                            b++;
                        }


                        acc = confusionMatrix[0][0] + confusionMatrix[1][1];
                        acc = acc/(count5+1);
                        acc = Math.round(acc * 100.0)/100.0;
                        acc_each_fold[classcounter1] = acc;
                        classcounter1++;


                    }
                    else {
                        counter3 = 2;
                        Category = Arrays.asList(outcome);
                        confusionMatrix = new int[counter3][counter3];
                        b = 0;
                        for(String instance : prediction) {
                            String oL = instance;
                            String aL = outcome[b];

                            int oLI = Category.indexOf(oL);

                            int aLI = Category.indexOf(aL);

                            if(aLI >= counter3 && oLI < counter3 ) {
                                confusionMatrix[1][oLI] += 1;
                            }
                            if(oLI >= counter3 && aLI < counter3) {

                                confusionMatrix[aLI][1] += 1;
                            }

                            if(oLI >= counter3 && aLI >= counter3 ) {
                                confusionMatrix[1][1] += 1;
                            }

                            if(aLI < counter3 && oLI < counter3 && aLI > -1 && oLI > -1) {

                                confusionMatrix[aLI][oLI] += 1;

                            }

                            if(oLI == -1) {
                                confusionMatrix[aLI][1] += 1;

                            }



                            b++;
                        }
                        acc = confusionMatrix[0][0] + confusionMatrix[1][1];
                        acc = acc/(length/folds);
                        acc = Math.round(acc * 100.0)/100.0;
                        acc_each_fold[classcounter1] = acc;
                        classcounter1++;

                    }
                }
                count5++;
                count7++;
                count10++;

            }

        }


        double foldAccuracy = 0;

        for(int i = 0;i<acc_each_fold.length;i++) {

            foldAccuracy = foldAccuracy + acc_each_fold[i];
        }

        foldAccuracy = foldAccuracy/folds;
        foldAccuracy = Math.round(foldAccuracy * 100);
        foldAccuracy = foldAccuracy/100;

        logAllFoldAccuracies[classcounter2] = foldAccuracy;
        classcounter2++;


        System.out.print("iteration: ");
        System.out.print(s+1+" ..................................................");
        System.out.println();
        if(s == folds-1) {
            double overallAccuracy = 0;
            for(int i = 0; i < logAllFoldAccuracies.length;i++) {
                overallAccuracy = overallAccuracy + logAllFoldAccuracies[i];

            }
            overallAccuracy = overallAccuracy/folds;
            overallAccuracy = Math.round(overallAccuracy * 100);
            overallAccuracy = overallAccuracy/100;

            System.out.println("Accuracy: " + overallAccuracy);
            System.out.println("Best k: " + k);
        }

        return crossval;
    }


}



