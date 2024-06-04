package Controllers;

import Models.DAO.UserDAO;
import Models.Entity.User;
import Models.Exceptions.InvalidEntityException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

public class UserController {
    public void register(String username, String user_password) throws SQLException, ParseException, InvalidEntityException {
        UserDAO dao = new UserDAO();
        User existingUser = dao.findByUsername(username);

        if (existingUser != null) {
            throw new InvalidEntityException("Username already exists.");
        }

        if (username == null || username.trim().isEmpty() || user_password == null || user_password.trim().isEmpty()) {
            throw new InvalidEntityException("Username and password cannot be empty.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setUserPassword(user_password);

        dao.save(newUser);
    }

    public void alter(long id, String username) throws SQLException, InvalidEntityException {
        UserDAO dao = new UserDAO();
        User existingUser = dao.findByUsername(username);

        if (username == null || username.trim().isEmpty()) {
            throw new InvalidEntityException("Username cannot be empty.");
        }

        if (existingUser != null) {
            throw new InvalidEntityException("Username already exists.");
        }

        User user = new User();
        user.setId(id);
        user.setUsername(username);

        new UserDAO().update(user);
    }

    public void delete(long id) throws SQLException {
        new UserDAO().delete(id);
    }

    public List<User> listUsers() {
        UserDAO dao = new UserDAO();
        try {
            return dao.findAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Problemas ao localizar usuário: " + 
                e.getLocalizedMessage()
            );
        }
        return null;
    }

    public User searchUsername(String username) throws SQLException, InvalidEntityException {
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidEntityException("Username cannot be empty.");
        }
        
        UserDAO dao = new UserDAO();
        User user = dao.findByUsername(username);
        
        if (user == null) {
            throw new InvalidEntityException("User not found.");
        }

        return user;
    }
}
