package Models;
import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/poo_pjbl";
    private static final String user = "root";
    private static final String password = "123456";

    private static Connection conn;

    public static Connection getMyConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}