package Models.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.GenericDAO;
import Models.Entity.User;

public class UserDAO extends GenericDAO {

    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO User (username, user_password) VALUES (?, ?)";
        save(sql, user.getUsername(), user.getUserPassword());
    }

    public void alterUser(User user) throws SQLException {
        String sql = "UPDATE User SET username = ?, user_password = ? WHERE id = ?";
        update(sql, user.getId(), user.getUsername());
    }

    public void deleteUser(long id) throws SQLException {
        String sql = "DELETE FROM User WHERE id = ?";
        delete(sql, id);
    }

    public List<User> findUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                users.add(user);
            }
        }

        return users;
    }

    public User findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM User WHERE username = ?";
        User user = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                }
            }
        }

        return user;
    }
}