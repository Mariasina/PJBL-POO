package Controllers;

import Models.DAO.UserDAO;
import Models.Entity.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

public class UserController {
    public void register(String username, String user_password) throws SQLException, ParseException {
        UserDAO dao = new UserDAO();
        User existingUser = dao.findByUsername(username);

        if (existingUser != null) {
            throw new SQLException("Username already exists.");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setUserPassword(user_password);

        dao.save(newUser);
    }

    public void alter(long id, String username, String user_password) throws ParseException, SQLException {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setUserPassword(user_password);

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

    public User searchUsername(String username) throws SQLException {
        UserDAO dao = new UserDAO();
        return dao.findByUsername(username);
    }
}
