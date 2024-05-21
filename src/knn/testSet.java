package knn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class testSet
{
    int ageTest;
    String workclassTest;
    int fnlwgtTest;
    String educationTest;
    int education_numTest;
    String marital_statusTest;
    String occupationTest;
    String relationshipTest;
    String raceTest;
    String sexTest;
    int capitalgainTest;
    int capitallossTest;
    int hoursperweekTest;
    String nativeCountryTest;
    String earnsTest;




    public testSet(int ageTest, String workclassTest, int fnlwgtTest, String educationTest, int education_numTest,
                   String marital_statusTest, String occupationTest, String relationshipTest, String raceTest, String sexTest,
                   int capitalgainTest, int capitallossTest, int hoursperweekTest, String nativeCountryTest, String earnsTest) {
        this.ageTest = ageTest;
        this.workclassTest = workclassTest;
        this.fnlwgtTest = fnlwgtTest;
        this.educationTest = educationTest;
        this.education_numTest = education_numTest;
        this.marital_statusTest = marital_statusTest;
        this.occupationTest = occupationTest;
        this.relationshipTest = relationshipTest;
        this.raceTest = raceTest;
        this.sexTest = sexTest;
        this.capitalgainTest = capitalgainTest;
        this.capitallossTest = capitallossTest;
        this.hoursperweekTest = hoursperweekTest;
        this.nativeCountryTest = nativeCountryTest;
        this.earnsTest = earnsTest;
    }
    @Override
    public String toString() {
        return "ageTest=" + ageTest + ", workclassTest=" + workclassTest + ", fnlwgtTest=" + fnlwgtTest
                + ", educationTest=" + educationTest + ", education_numTest=" + education_numTest + ", marital_statusTest="
                + marital_statusTest + ", occupationTest=" + occupationTest + ", relationshipTest=" + relationshipTest
                + ", raceTest=" + raceTest + ", sexTest=" + sexTest + ", capitalgainTest=" + capitalgainTest
                + ", capitallossTest=" + capitallossTest + ", hoursperweekTest=" + hoursperweekTest + ", nativeCountryTest="
                + nativeCountryTest + ", earnsTest=" + earnsTest;
    }
    public int getAgeTest() {
        return ageTest;
    }
    public void setAgeTest(int ageTest) {
        this.ageTest = ageTest;
    }
    public String getWorkclassTest() {
        return workclassTest;
    }
    public void setWorkclassTest(String workclassTest) {
        this.workclassTest = workclassTest;
    }
    public int getFnlwgtTest() {
        return fnlwgtTest;
    }
    public void setFnlwgtTest(int fnlwgtTest) {
        this.fnlwgtTest = fnlwgtTest;
    }
    public String getEducationTest() {
        return educationTest;
    }
    public void setEducationTest(String educationTest) {
        this.educationTest = educationTest;
    }
    public int getEducation_numTest() {
        return education_numTest;
    }
    public void setEducation_numTest(int education_numTest) {
        this.education_numTest = education_numTest;
    }
    public String getMarital_statusTest() {
        return marital_statusTest;
    }
    public void setMarital_statusTest(String marital_statusTest) {
        this.marital_statusTest = marital_statusTest;
    }
    public String getOccupationTest() {
        return occupationTest;
    }
    public void setOccupationTest(String occupationTest) {
        this.occupationTest = occupationTest;
    }
    public String getRelationshipTest() {
        return relationshipTest;
    }
    public void setRelationshipTest(String relationshipTest) {
        this.relationshipTest = relationshipTest;
    }
    public String getRaceTest() {
        return raceTest;
    }
    public void setRaceTest(String raceTest) {
        this.raceTest = raceTest;
    }
    public String getSexTest() {
        return sexTest;
    }
    public void setSexTest(String sexTest) {
        this.sexTest = sexTest;
    }
    public int getCapitalgainTest() {
        return capitalgainTest;
    }
    public void setCapitalgainTest(int capitalgainTest) {
        this.capitalgainTest = capitalgainTest;
    }
    public int getCapitallossTest() {
        return capitallossTest;
    }
    public void setCapitallossTest(int capitallossTest) {
        this.capitallossTest = capitallossTest;
    }
    public int getHoursperweekTest() {
        return hoursperweekTest;
    }
    public void setHoursperweekTest(int hoursperweekTest) {
        this.hoursperweekTest = hoursperweekTest;
    }
    public String getNativeCountryTest() {
        return nativeCountryTest;
    }
    public void setNativeCountryTest(String nativeCountryTest) {
        this.nativeCountryTest = nativeCountryTest;
    }
    public String getEarnsTest() {
        return earnsTest;
    }
    public void setEarnsTest(String earnsTest) {
        this.earnsTest = earnsTest;
    }

}

class processTestSamples {
    int numRows = 200;  //increase this value to increase the number of samples in test set

    public testSet[][] testingSet() throws FileNotFoundException {
        Scanner sc2 = new Scanner(new File("src/adult.test.csv"));
        int numColumns = 15;
        String eachline = sc2.nextLine();

        String[][] subSample = new String[numRows][numColumns];
        int counter2 = 0;

        while(sc2.hasNextLine() == true) {
            if(counter2 < numRows) {
                eachline = sc2.nextLine();
                subSample[counter2] = eachline.split(",");
                counter2++;
            }
            else {
                break;
            }
        }

        sc2.close();

        int ageCVTest;
        String workclassCVTest;
        int fnlwgtCVTest;
        String educationCVTest;
        int education_numCVTest;
        String marital_statusCVTest;
        String occupationCVTest;
        String relationshipCVTest;
        String raceCVTest;
        String sexCVTest;
        int capitalgainCVTest;
        int capitallossCVTest;
        int hoursperweekCVTest;
        String nativeCountryCVTest;
        String earnsCVTest;


        testSet prepTrainingData[][] = new testSet[numRows][numColumns];


        for(int i = 0; i < subSample.length;i++) {
            for(int j = 0; j < subSample[0].length;j++) {
                if(j < 13 && j == 0) {
                    ageCVTest = (int) Double.parseDouble(subSample[i][j]);
                    fnlwgtCVTest = (int) Double.parseDouble(subSample[i][j+2]);
                    education_numCVTest = (int) Double.parseDouble(subSample[i][j+4]);
                    capitalgainCVTest = (int) Double.parseDouble(subSample[i][j+10]);
                    capitallossCVTest = (int) Double.parseDouble(subSample[i][j+11]);
                    hoursperweekCVTest = (int) Double.parseDouble(subSample[i][j+12]);

                    workclassCVTest = subSample[i][j+1];
                    educationCVTest = subSample[i][j+3];
                    marital_statusCVTest = subSample[i][j+5];
                    occupationCVTest = subSample[i][j+6];
                    relationshipCVTest = subSample[i][j+7];
                    raceCVTest = subSample[i][j+8];
                    sexCVTest = subSample[i][j+9];
                    nativeCountryCVTest = subSample[i][j+13];
                    earnsCVTest = subSample[i][j+14];
                    prepTrainingData[i][j] = new testSet(ageCVTest,workclassCVTest,fnlwgtCVTest,educationCVTest,education_numCVTest,marital_statusCVTest,occupationCVTest,relationshipCVTest,raceCVTest,sexCVTest,capitalgainCVTest,capitallossCVTest,hoursperweekCVTest,nativeCountryCVTest,earnsCVTest);

                }}}


        int k3 = 0;
        testSet unNullifyArray[][] = new testSet[numRows][numColumns];

        for (int i = 0; i < prepTrainingData.length; i++) {
            ArrayList<testSet> list = new ArrayList<testSet>();
            for (int j = 0; j < prepTrainingData[i].length; j++) {
                if (prepTrainingData[i][j] != null) {
                    list.add(prepTrainingData[i][j]);
                }
            }
            unNullifyArray[k3++] = list.toArray(new testSet[list.size()]);
        }

        return unNullifyArray;
    }


    //all rows in test set in number

    public int[][] twodRowsTest(testSet[][] testset) throws FileNotFoundException {

        int num1 = testset.length;
        int num2 = 14;

        int[][] eachrowintestset = new int[num1][num2];
        int counter = 0;
        for(int i = 0; i < num1;i++) {
            eachrowintestset[i][counter] = testset[i][0].getAgeTest();
            eachrowintestset[i][counter+1] = new TestSet_StringtoInt().workclassToIntTest(testset)[i];
            eachrowintestset[i][counter+2] = testset[i][0].getFnlwgtTest();
            eachrowintestset[i][counter+3] = new TestSet_StringtoInt().educationToIntTest(testset)[i];
            eachrowintestset[i][counter+4] = testset[i][0].getEducation_numTest();
            eachrowintestset[i][counter+5] = new TestSet_StringtoInt().maritalstatusToIntTest(testset)[i];
            eachrowintestset[i][counter+6] = new TestSet_StringtoInt().occupationToIntTest(testset)[i];
            eachrowintestset[i][counter+7] = new TestSet_StringtoInt().relationshipToIntTest(testset)[i];
            eachrowintestset[i][counter+8] = new TestSet_StringtoInt().raceToIntTest(testset)[i];
            eachrowintestset[i][counter+9] = new TestSet_StringtoInt().sexToIntTest(testset)[i];
            eachrowintestset[i][counter+10] = testset[i][0].getCapitalgainTest();
            eachrowintestset[i][counter+11] = testset[i][0].getCapitallossTest();
            eachrowintestset[i][counter+12] = testset[i][0].getHoursperweekTest();
            eachrowintestset[i][counter+13] = new TestSet_StringtoInt().nativecountryToIntTest(testset)[i];



        }
        return eachrowintestset;

    }


}












class TestSet_StringtoInt {

    public int[] workclassToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getWorkclassTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getWorkclassTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] educationToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getEducationTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getEducationTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }

    public int[] maritalstatusToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getMarital_statusTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getMarital_statusTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }




    public int[] occupationToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getOccupationTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getOccupationTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] relationshipToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getRelationshipTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getRelationshipTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }

    public int[] raceToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getRaceTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getRaceTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] sexToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getSexTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getSexTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] nativecountryToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getNativeCountryTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getNativeCountryTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] earnsToIntTest(testSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getEarnsTest();
        }

        HashSet<String> uniqueVals = new HashSet<String>();

        for(int i = 0; i < processAllStrings.length;i++) {

            uniqueVals.add(processAllStrings[i]);
        }
        Object[] AlbhabeticOrder = uniqueVals.toArray();
        Arrays.sort(AlbhabeticOrder);
        int[] finalise = new int[ts.length];
        for(int j = 0; j < AlbhabeticOrder.length;j++) {
            for(int i = 0;i < ts.length;i++) {

                if(ts[i][0].getEarnsTest().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }
}



