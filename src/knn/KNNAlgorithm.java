package knn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class KNNAlgorithm {
    int k = 100;        //increase this number to increase number of neighbours

    double[]  neighbours = new double[k];

    public double euclideanDistance(testSet[][] data1,trainingSet[][] data2,int length,int s) {
        double d = 0;

        //for everytime s increments in knn function, it will pass into here, acting like a for loop
        d+= Math.pow(data1[s][0].getAgeTest() - data2[s][0].getAge(), 2);
        d+= Math.pow(new TestSet_StringtoInt().workclassToIntTest(data1)[s] - new trainingSet_StringtoInt().workclassToInt(data2)[s] , 2);
        d+= Math.pow(data1[s][0].getFnlwgtTest() - data2[s][0].getFnlwgt(), 2);
        d+= Math.pow(new TestSet_StringtoInt().educationToIntTest(data1)[s] - new trainingSet_StringtoInt().educationToInt(data2)[s] , 2);
        d+= Math.pow(data1[s][0].getEducation_numTest() - data2[s][0].getEducation_num(), 2);
        d+= Math.pow(new TestSet_StringtoInt().maritalstatusToIntTest(data1)[s] - new trainingSet_StringtoInt().maritalstatusToInt(data2)[s] , 2);
        d+= Math.pow(new TestSet_StringtoInt().occupationToIntTest(data1)[s] - new trainingSet_StringtoInt().occupationToInt(data2)[s] , 2);
        d+= Math.pow(new TestSet_StringtoInt().relationshipToIntTest(data1)[s] - new trainingSet_StringtoInt().relationshipToInt(data2)[s] , 2);
        d+= Math.pow(new TestSet_StringtoInt().raceToIntTest(data1)[s] - new trainingSet_StringtoInt().raceToInt(data2)[s] , 2);
        d+= Math.pow(new TestSet_StringtoInt().sexToIntTest(data1)[s] - new trainingSet_StringtoInt().sexToInt(data2)[s] , 2);
        d+= Math.pow(data1[s][0].getCapitalgainTest() - data2[s][0].getCapitalgain(), 2);
        d+= Math.pow(data1[s][0].getCapitallossTest() - data2[s][0].getCapitalloss(), 2);
        d+= Math.pow(data1[s][0].getHoursperweekTest() - data2[s][0].getHoursperweek(), 2);
        d+= Math.pow(new TestSet_StringtoInt().nativecountryToIntTest(data1)[s] - new trainingSet_StringtoInt().nativecountryToInt(data2)[s] , 2);
        d+= Math.pow(new TestSet_StringtoInt().earnsToIntTest(data1)[s] - new trainingSet_StringtoInt().earnsToInt(data2)[s] , 2);


        return Math.sqrt(d);

    }


    public void knnModel(trainingSet[][] trainingSet,testSet[][] testInstance,int k) {

        HashMap<Integer,Double> distances = new HashMap<Integer,Double>();

        int length = testInstance[0].length;

        for(int i = 0; i < trainingSet.length;i++) {

            double dist = euclideanDistance(testInstance,trainingSet,length,i);

            distances.put(i, dist);
        }

        Map<Integer, Double> sortedDistancesHashMap = distances.entrySet().stream().sorted(comparingByValue()).collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,LinkedHashMap::new));


        Object[][] sortedDistances2DArray = new Object[sortedDistancesHashMap.size()][2];
        int count = 0;
        for(Map.Entry<Integer, Double> entry : sortedDistancesHashMap.entrySet()){
            sortedDistances2DArray[count][0] = entry.getKey();
            sortedDistances2DArray[count][1] = entry.getValue();
            count++;
        }

        neighbours = new double[k];
        for(int i = 0; i < k;i++) {

            neighbours[i] = (int)sortedDistances2DArray[i][0];


        }

        HashMap<String,Integer> Votes = new HashMap<String,Integer>();
        Integer counter = 0;
        for(int i = 0; i < neighbours.length;i++) {
            String resp = trainingSet[(int) neighbours[i]][0].getEarns();
            counter = Votes.get(resp);
            if (counter == null){
                counter = 0;
            }
            counter++;
            Votes.put(resp, counter);
        }




        HashMap<String,Integer> Votes2 = new HashMap<String,Integer>();
        Integer counter2 = 0;
        for(int i = 0; i < neighbours.length;i++) {
            String resp = testInstance[(int) neighbours[i]][0].getEarnsTest();
            counter2 = Votes2.get(resp);
            if (counter2 == null){
                counter2 = 0;
            }
            counter2++;
            Votes2.put(resp, counter2);
        }


        System.out.println("Predicted Class: " +Votes);

        System.out.println(k+"-Nearest Neighbours: " + Arrays.toString(neighbours));


        String[] prediction = new String[testInstance.length];

        String[] outcome = new String[testInstance.length];
        int tempcount = 0;
        for(int i = 0; i < prediction.length;i++) {

            String resp = testInstance[(int) neighbours[tempcount]][0].getEarnsTest();
            String resp2 = testInstance[i][0].getEarnsTest();
            prediction[i] = resp;
            outcome[i] = resp2;
            if(tempcount == neighbours.length-1) {
                tempcount = 0;
            }
            else {
                tempcount++;}
        }


        String temp[] = new String[outcome.length+prediction.length];

        for(int i = 0; i < temp.length;i++) {
            if(i < temp.length/2) {

                temp[i] = outcome[i];
            }
            if(i >= temp.length/2  && i < temp.length) {
                temp[i] = prediction[i - prediction.length];
            }
        }
        int counter3 = 2;

        java.util.List<String> Category = Arrays.asList(outcome);
        int[][] confusionMatrix = new int[counter3][counter3];
        int b = 0;
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
        System.out.println();
        System.out.println("Test Set Confusion Matrix: " + Arrays.deepToString(confusionMatrix));

        double sensitivity = confusionMatrix[0][0] + confusionMatrix[0][1];
        double sensitivity2 = confusionMatrix[0][0]/sensitivity;

        sensitivity2 = Math.round(sensitivity2 * 100);
        sensitivity2 = sensitivity2/100;

        double precision = confusionMatrix[0][0] + confusionMatrix[1][0];
        double precision2 = confusionMatrix[0][0]/precision;
        precision2 = Math.round(precision2 * 100);
        precision2 = precision2/100;


        double specificity = confusionMatrix[1][1] + confusionMatrix[1][0];
        double specificity2 = confusionMatrix[1][1]/specificity;
        specificity2 = Math.round(specificity2 * 100);
        specificity2 = specificity2/100;


        System.out.println("Sensitivity: " + sensitivity2);
        System.out.println("precision: " + precision2);
        System.out.println("Specificity: " + specificity2);



    }
}