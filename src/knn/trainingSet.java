package knn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class trainingSet
{
    int age;
    String workclass;
    int fnlwgt;
    String education;
    int education_num;
    String marital_status;
    String occupation;
    String relationship;
    String race;
    String sex;
    int capitalgain;
    int capitalloss;
    int hoursperweek;
    String nativeCountry;
    String earns;

    public trainingSet(int age, String workclass, int fnlwgt, String education, int education_num, String marital_status,
                       String occupation, String relationship, String race, String sex, int capitalgain, int capitalloss,
                       int hoursperweek, String nativeCountry, String earns) {
        super();
        this.age = age;
        this.workclass = workclass;
        this.fnlwgt = fnlwgt;
        this.education = education;
        this.education_num = education_num;
        this.marital_status = marital_status;
        this.occupation = occupation;
        this.relationship = relationship;
        this.race = race;
        this.sex = sex;
        this.capitalgain = capitalgain;
        this.capitalloss = capitalloss;
        this.hoursperweek = hoursperweek;
        this.nativeCountry = nativeCountry;
        this.earns = earns;
    }
    @Override
    public String toString() {
        return "age=" + age + ", workclass=" + workclass + ", fnlwgt=" + fnlwgt + ", education=" + education
                + ", education_num=" + education_num + ", marital_status=" + marital_status + ", occupation=" + occupation
                + ", relationship=" + relationship + ", race=" + race + ", sex=" + sex + ", capitalgain=" + capitalgain
                + ", capitalloss=" + capitalloss + ", hoursperweek=" + hoursperweek + ", nativeCountry=" + nativeCountry
                + ", earns=" + earns;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getWorkclass() {
        return workclass;
    }
    public void setWorkclass(String workclass) {
        this.workclass = workclass;
    }
    public int getFnlwgt() {
        return fnlwgt;
    }
    public void setFnlwgt(int fnlwgt) {
        this.fnlwgt = fnlwgt;
    }
    public String getEducation() {
        return education;
    }
    public void setEducation(String education) {
        this.education = education;
    }
    public int getEducation_num() {
        return education_num;
    }
    public void setEducation_num(int education_num) {
        this.education_num = education_num;
    }
    public String getMarital_status() {
        return marital_status;
    }
    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }
    public String getOccupation() {
        return occupation;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public String getRelationship() {
        return relationship;
    }
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
    public String getRace() {
        return race;
    }
    public void setRace(String race) {
        this.race = race;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getCapitalgain() {
        return capitalgain;
    }
    public void setCapitalgain(int capitalgain) {
        this.capitalgain = capitalgain;
    }
    public int getCapitalloss() {
        return capitalloss;
    }
    public void setCapitalloss(int capitalloss) {
        this.capitalloss = capitalloss;
    }
    public int getHoursperweek() {
        return hoursperweek;
    }
    public void setHoursperweek(int hoursperweek) {
        this.hoursperweek = hoursperweek;
    }
    public String getNativeCountry() {
        return nativeCountry;
    }
    public void setNativeCountry(String nativeCountry) {
        this.nativeCountry = nativeCountry;
    }
    public String getEarns() {
        return earns;
    }
    public void setEarns(String earns) {
        this.earns = earns;
    }
}

class processTrainSamples {
    int numRows = 200;    //increase this number to increase the number of samples. make sure you increase the foldArr method numRows to the same value as this one


    public int[] foldArr() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/adult.train.5fold.csv"));
        int numColumns = 16;
        String eachline = sc.nextLine();

        String[][] subSample = new String[numRows][numColumns];
        int counter = 0;

        while(sc.hasNextLine() == true) {

            if(counter < numRows) {
                eachline = sc.nextLine();
                subSample[counter] = eachline.split(",");
                counter++;
            }
            else {
                break;
            }

        }
        sc.close();
        String[][] keepFoldAttribute = new String[numRows][numColumns];

        for (int i = 0; i < keepFoldAttribute.length; i++)
        {
            for (int j = 0; j < keepFoldAttribute[0].length; j++)
            {
                keepFoldAttribute[i][j] = subSample[i][j];
            }
        }
        int[] allFoldAttributes = new int[keepFoldAttribute.length];

        int folds;

        for(int i = 0; i < keepFoldAttribute.length;i++ ) {
            folds = (int) Double.parseDouble(keepFoldAttribute[i][15]);
            allFoldAttributes[i] = folds;
        }

        for(int i = 0; i < keepFoldAttribute.length;i++ ) {
            allFoldAttributes[i] = allFoldAttributes[i] -1;
        }


        return allFoldAttributes;
    }



    public trainingSet[][] trainSet() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/adult.train.5fold.csv"));
        int numColumns = 16;
        String eachline = sc.nextLine();

        String[][] subSample = new String[numRows][numColumns];
        int counter = 0;

        while(sc.hasNextLine() == true) {

            if(counter < numRows) {
                eachline = sc.nextLine();
                subSample[counter] = eachline.split(",");
                counter++;
            }
            else {
                break;
            }

        }
        sc.close();

        String[][] removeFoldAttribute = new String[numRows][numColumns - 1];

        for (int i = 0; i < removeFoldAttribute.length; i++)
        {
            for (int j = 0; j < removeFoldAttribute[0].length; j++)
            {
                removeFoldAttribute[i][j] = subSample[i][j];
            }
        }

        int ageCV;
        String workclassCV;
        int fnlwgtCV;
        String educationCV;
        int education_numCV;
        String marital_statusCV;
        String occupationCV;
        String relationshipCV;
        String raceCV;
        String sexCV;
        int capitalgainCV;
        int capitallossCV;
        int hoursperweekCV;
        String nativeCountryCV;
        String earnsCV;
        trainingSet prepTrainingData[][] = new trainingSet[numRows][numColumns - 1];


        for(int i = 0; i < removeFoldAttribute.length;i++) {
            for(int j = 0; j < removeFoldAttribute[0].length;j++) {
                if(j < 13 && j == 0) {
                    ageCV = (int) Double.parseDouble(removeFoldAttribute[i][j]);
                    fnlwgtCV = (int) Double.parseDouble(removeFoldAttribute[i][j+2]);
                    education_numCV = (int) Double.parseDouble(removeFoldAttribute[i][j+4]);
                    capitalgainCV = (int) Double.parseDouble(removeFoldAttribute[i][j+10]);
                    capitallossCV = (int) Double.parseDouble(removeFoldAttribute[i][j+11]);
                    hoursperweekCV = (int) Double.parseDouble(removeFoldAttribute[i][j+12]);

                    workclassCV = removeFoldAttribute[i][j+1];
                    educationCV = removeFoldAttribute[i][j+3];
                    marital_statusCV = removeFoldAttribute[i][j+5];
                    occupationCV = removeFoldAttribute[i][j+6];
                    relationshipCV = removeFoldAttribute[i][j+7];
                    raceCV = removeFoldAttribute[i][j+8];
                    sexCV = removeFoldAttribute[i][j+9];
                    nativeCountryCV = removeFoldAttribute[i][j+13];
                    earnsCV = removeFoldAttribute[i][j+14];
                    prepTrainingData[i][j] = new trainingSet(ageCV,workclassCV,fnlwgtCV,educationCV,education_numCV,marital_statusCV,occupationCV,relationshipCV,raceCV,sexCV,capitalgainCV,capitallossCV,hoursperweekCV,nativeCountryCV,earnsCV);

                }}}

        int k3 = 0;
        trainingSet unNullifyArray[][] = new trainingSet[numRows][numColumns - 1];

        for (int i = 0; i < prepTrainingData.length; i++) {
            ArrayList<trainingSet> list = new ArrayList<trainingSet>();
            for (int j = 0; j < prepTrainingData[i].length; j++) {
                if (prepTrainingData[i][j] != null) {
                    list.add(prepTrainingData[i][j]);
                }
            }
            unNullifyArray[k3++] = list.toArray(new trainingSet[list.size()]);
        }


        return unNullifyArray;

    }
    //all rows in training set in numbers

    public int[][] twodRowsTrain(trainingSet[][] trainset) throws FileNotFoundException {
        int num1 = trainset.length;
        int num2 = 14;

        int[][] eachrowintestset = new int[num1][num2];
        int counter = 0;
        for(int i = 0; i < num1;i++) {
            eachrowintestset[i][counter] = trainset[i][0].getAge();
            eachrowintestset[i][counter+1] = new trainingSet_StringtoInt().workclassToInt(trainset)[i];
            eachrowintestset[i][counter+2] = trainset[i][0].getFnlwgt();
            eachrowintestset[i][counter+3] = new trainingSet_StringtoInt().educationToInt(trainset)[i];
            eachrowintestset[i][counter+4] = trainset[i][0].getEducation_num();
            eachrowintestset[i][counter+5] = new trainingSet_StringtoInt().maritalstatusToInt(trainset)[i];
            eachrowintestset[i][counter+6] = new trainingSet_StringtoInt().occupationToInt(trainset)[i];
            eachrowintestset[i][counter+7] = new trainingSet_StringtoInt().relationshipToInt(trainset)[i];
            eachrowintestset[i][counter+8] = new trainingSet_StringtoInt().raceToInt(trainset)[i];
            eachrowintestset[i][counter+9] = new trainingSet_StringtoInt().sexToInt(trainset)[i];
            eachrowintestset[i][counter+10] = trainset[i][0].getCapitalgain();
            eachrowintestset[i][counter+11] = trainset[i][0].getCapitalloss();
            eachrowintestset[i][counter+12] = trainset[i][0].getHoursperweek();
            eachrowintestset[i][counter+13] = new trainingSet_StringtoInt().nativecountryToInt(trainset)[i];


        }

        return eachrowintestset;

    }


}




class trainingSet_StringtoInt {

    //returns the whole columns
    public int[] workclassToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getWorkclass();
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

                if(ts[i][0].getWorkclass().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] educationToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getEducation();
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

                if(ts[i][0].getEducation().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }

    public int[] maritalstatusToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getMarital_status();
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

                if(ts[i][0].getMarital_status().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }




    public int[] occupationToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getOccupation();
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

                if(ts[i][0].getOccupation().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] relationshipToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getRelationship();
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

                if(ts[i][0].getRelationship().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }

    public int[] raceToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getRace();
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

                if(ts[i][0].getRace().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] sexToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getSex();
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

                if(ts[i][0].getSex().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] nativecountryToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getNativeCountry();
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

                if(ts[i][0].getNativeCountry().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }


    public int[] earnsToInt(trainingSet[][] ts) {

        String[] processAllStrings = new String[ts.length];

        for(int i = 0; i < processAllStrings.length;i++) {

            processAllStrings[i]  = ts[i][0].getEarns();
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

                if(ts[i][0].getEarns().equals(AlbhabeticOrder[j])) {
                    finalise[i] = Arrays.asList(AlbhabeticOrder).indexOf(AlbhabeticOrder[j]);
                }

            }
        }

        return finalise;

    }

}
