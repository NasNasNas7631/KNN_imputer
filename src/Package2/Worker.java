package Package2;

import java.util.Scanner;


public class Worker extends Student {
    private int salary;

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public static void inputInfo(Scanner scanner) {
        Worker worker = new Worker();
        System.out.println("Введите имя работника: ");
        worker.setName(scanner.nextLine());
        System.out.println("Введите возраст работника: ");
        String str = scanner.nextLine();
        worker.setAge(Integer.parseInt(str));
        System.out.println("Введите зарплату работника: ");
        String str2 = scanner.nextLine();
        worker.setSalary(Integer.parseInt(str2));

        DBhandler.saveString(worker);
    }
}