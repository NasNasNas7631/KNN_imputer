package Package2;

import Package2.DBhandler;
import Package1.ExcelExporter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("""
                    1. Вывести все таблицы из MySQL.
                    2. Создать таблицу в MySQL.
                    3. Вписать данные в поля
                    9. Сохранить все данные (вышеполученные результаты) из MySQL в Excel и вывести на экран 
                    0. Выход.""");

            String s = scanner.nextLine();
            choice = Integer.parseInt(s);
            handleUserChoice(choice, scanner, DBhandler.getTableName());
        } while (choice != 0);
    }
    private static void handleUserChoice (int choice, Scanner scanner, String tableName) {
        switch (choice) {
            case 1:
                DBhandler.showAllTables();
                break;
            case 2:
                System.out.println("Введите название для создания таблицы:");
                DBhandler.setTableName(scanner.nextLine());
                DBhandler.createTable();
                break;
            case 3:
                Worker.inputInfo(scanner);
                break;
            case 6:
                ExcelExporter exporter = new ExcelExporter();
                exporter.export();
            case 0:
                System.out.println("Выход из программы...");
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
}
