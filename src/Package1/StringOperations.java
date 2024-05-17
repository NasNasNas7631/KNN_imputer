import java.util.Scanner;

public class StringOperations {
    public static void inputStrings(Scanner scanner) {
        System.out.println("Введите первую строку: ");
        // scanner.next();
        String str1 = scanner.nextLine();
        System.out.println("Введите вторую строку: ");
        String str2 = scanner.nextLine();

        // Вывод строк в терминал
        System.out.println("Введены строки:\n(1) " + str1 + "\n(2) " + str2);
        // Сохранение в бд
        DBhandler.saveString(str1, str2);

    }

    public static void StringReverse()
    {
        StringBuffer str1 = new StringBuffer();
        str1.append(DBhandler.getStrFromDB(1));
        str1.reverse();
        DBhandler.insertStrReverse(String.valueOf(str1));
    }

    public static void StringToString()
    {
        StringBuffer str0 = new StringBuffer();
        str0.append(DBhandler.getStrFromDB(1)).append(" ").append(DBhandler.getStrFromDB(2));
        DBhandler.insertStringToString(String.valueOf(str0));
    }
    public static void NumbersIntegrity() {
        try {
            int number = scanner.nextInt();

            if (number % 1 == 0) {
                System.out.println("Целое");
                DBhandler.saveString("Целое");

            } else {
                System.out.println("Не целое");
                DBhandler.saveString("Не целое");
            }
        }
    }

    public static void NumbersParity() {
        try {
            int number = scanner.nextInt();

            if (number % 2 == 0) {
                System.out.println("Четное");
                DBhandler.saveString("Четное");
            } else {
                System.out.println("Нечетное");
                DBhandler.saveString("Нечетное");
            }
        }
    }

}