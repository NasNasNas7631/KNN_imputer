package Package3;

import java.util.ArrayList;
import java.util.Collections;

public class SortedStudents extends Students {
    @Override
    public void inputStudentDetails() {
        super.inputStudentDetails();
        ArrayList<String> sortedList = new ArrayList(this.getStudentName());
        Collections.sort(sortedList);

        System.out.println("Sorted student list:");
        System.out.format("%-15s %-20s %-20s %-15s\n", "ID", "Specialty", "Name", "Group");
        for (String name : sortedList) {
            int index = this.getStudentName().indexOf(name);
            System.out.format("%-15s %-20s %-20s %-15s\n", this.getStudentID().get(index), this.getStudentSpecialty().get(index), this.getStudentName().get(index), this.getStudentGroup().get(index));
        }
    }
}