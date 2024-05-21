package Package3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Students {
    private int numOfStudents;
    private ArrayList<String> studentID = new ArrayList();
    private ArrayList<String> studentSpecialty = new ArrayList();
    private ArrayList<String> studentName = new ArrayList();
    private ArrayList<String> studentGroup = new ArrayList();

    public void inputStudentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of students:");
        numOfStudents = scanner.nextInt();

        for (int i = 0; i < numOfStudents; i++) {
            System.out.println("Enter student ID:");
            studentID.add(scanner.next());

            System.out.println("Enter student specialty:");
            studentSpecialty.add(scanner.next());

            System.out.println("Enter student name:");
            studentName.add(scanner.next());

            System.out.println("Enter student group:");
            studentGroup.add(scanner.next());
        }
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }

    public ArrayList<String> getStudentID() {
        return studentID;
    }

    public void setStudentID(ArrayList<String> studentID) {
        this.studentID = studentID;
    }

    public ArrayList<String> getStudentSpecialty() {
        return studentSpecialty;
    }

    public void setStudentSpecialty(ArrayList<String> studentSpecialty) {
        this.studentSpecialty = studentSpecialty;
    }

    public ArrayList<String> getStudentName() {
        return studentName;
    }

    public void setStudentName(ArrayList<String> studentName) {
        this.studentName = studentName;
    }

    public ArrayList<String> getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(ArrayList<String> studentGroup) {
        this.studentGroup = studentGroup;
    }
}

