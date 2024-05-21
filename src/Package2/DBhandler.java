package Package2;
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
                "name_ VARCHAR(255), " +
                "age INT, " +
                "salary INT)";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql_);
            System.out.println("Таблица " + tableName + " успешно создана.");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы " + tableName);
        }
    }

    public static void saveString(Worker worker) {

        System.out.println("Name: " + worker.getName());

        System.out.println("Age: " + worker.getAge());
        System.out.println("Salary: " + worker.getSalary());

        String query = "INSERT INTO " + tableName + " (name_, age, salary) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);  ) {
            stmt.setString(1,worker.getName());
            stmt.setInt(2, worker.getAge());
            stmt.setInt(3, worker.getSalary());
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
}

