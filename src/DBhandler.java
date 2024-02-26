import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBhandler {
    private static final String URL = "jdbc:mysql://localhost:3306/newdb";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static String tableName = "string_db";

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String newName) {
        tableName = newName;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createTable() {
        String sql_ = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "string1 VARCHAR(255), " +
                "string2 VARCHAR(255), " +
                "reversed_strings VARCHAR(255), "+
                "string_to_string VARCHAR(255))";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql_);
            System.out.println("Таблица " + tableName + " успешно создана.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы " + tableName);
        }
    }

    public static void saveString(String string1, String string2) {
        String query = "INSERT INTO " + tableName + " (string1, string2) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, string1);
            stmt.setString(2, string2);
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
        }
    }

    // Метод для отображения всех таблиц
    public static void showAllTables() {
        // Получаем список всех таблиц
        String sqlTables = "SHOW TABLES;";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            ResultSet rsTables = stmt.executeQuery(sqlTables);
            while (rsTables.next()) {
                String name = rsTables.getString(1);
                System.out.println(name);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка таблиц");
        }
    }

    public static String getStrFromDB(int strNum) {
        String str = "";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
        ) {
            String querySelect = "select string1, string2 from " + tableName + " ORDER BY id DESC LIMIT 1;";
            ResultSet rsTables = stmt.executeQuery(querySelect);
            while (rsTables.next()) {
                str = rsTables.getString(strNum);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении данных из таблицы");
        }
        return str;
    }

    public static void insertStrReverse(String strReverse) {
        System.out.println("Результат реверсирования строк : " + strReverse);
        String query = "UPDATE " + tableName + " SET  reversed_strings = '" + strReverse + "'ORDER BY id DESC LIMIT 1;";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
        }
    }

    public static void insertStringToString(String strSTS){
        System.out.println("Результат реверсирования строк : " + strSTS);
        String query = "UPDATE " + tableName + " SET  string_to_string = '" + strSTS + "'ORDER BY id DESC LIMIT 1;";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.executeUpdate();
            System.out.println("Результат операции сохранен в таблицу " + tableName);
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить результат операции в таблицу " + tableName);
        }
    }
}

