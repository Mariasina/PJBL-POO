package myconnection;
import java.sql.*;

public class MyConnection {
    private static final String url = "jdbc:mysql://localhost:3306/poo_pjbl";
    private static final String user = "root";
    private static final String password = "123456";

    private static Connection conn;

    public static Connection getMyConnection(){
        try{
            if(conn == null){
            
                conn = DriverManager.getConnection(url, user, password);
                return conn;
            }
            else{
                return conn;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}