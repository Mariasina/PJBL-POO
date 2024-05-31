package Models.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.GenericDAO;
import Models.Entity.User;

public class UserDAO extends GenericDAO<User> {

    @Override
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO User (username, user_password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getUserPassword());
            pstmt.execute();
        }
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE User SET username = ?, user_password = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setLong(3, user.getId());
            pstmt.execute();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM User WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setLong(1, id);
            pstmt.execute();
        }
    }

    @Override
    public User findById(long id) throws SQLException {
        String sql = "SELECT * FROM User WHERE id = ?";
        User user = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    user.setUserPassword(rs.getString("user_password"));
                }
            }
        }

        return user;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
             
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUsername(rs.getString("username"));
                user.setUserPassword(rs.getString("user_password"));
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
                    user.setUserPassword(rs.getString("user_password"));
                }
            }
        }

        return user;
    }
}
