package DAO;
import entity.User;
import myconnection.MyConnection;
import java.sql.*;


public class UserDAO{
    public void registerUser(User User){
        String sql = "insert into User (username) values (?)";

        PreparedStatement ps = null;

        try{
            ps = MyConnection.getMyConnection().prepareStatement(sql);
            ps.setString(1, User.getUsername());

            ps.execute();
            ps.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}