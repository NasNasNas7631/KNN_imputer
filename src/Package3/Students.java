package Package3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Students {
    private int numOfStudents;
    private ArrayList<String> studentID = new ArrayList<>();
    private ArrayList<String> studentSpecialty = new ArrayList<>();
    private ArrayList<String> studentName = new ArrayList<>();
    private ArrayList<String> studentGroup = new ArrayList<>();

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
}

public class SortedStudents extends Students {
    @Override
    public void inputStudentDetails() {
        super.inputStudentDetails();

        ArrayList<String> sortedList = new ArrayList<>(studentName);
        Collections.sort(sortedList);

        System.out.println("Sorted student list:");
        System.out.format("%-15s %-20s %-20s %-15s\n", "ID", "Specialty", "Name", "Group");
        for (String name : sortedList) {
            int index = studentName.indexOf(name);
            System.out.format("%-15s %-20s %-20s %-15s\n", studentID.get(index), studentSpecialty.get(index), studentName.get(index), studentGroup.get(index));
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SortedStudents sortedStudents = new SortedStudents();
        sortedStudents.inputStudentDetails();
    }
}