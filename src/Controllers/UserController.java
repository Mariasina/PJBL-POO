package Controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

import Models.DAO.UserDAO;
import Models.Entity.User;

public class UserController {
    public void register(String username, String user_password) throws SQLException, ParseException {
        User User = new User();
        User.setUsername(username);
        User.setUserPassword(user_password);

        new UserDAO().save(User);
    }

    public void alter(long id, String username, String user_password) throws ParseException, SQLException {
		User User = new User();
        User.setId(id);
        User.setUsername(username);
        User.setUserPassword(user_password);

        new UserDAO().update(User);
    }

    public void delete(long id) throws SQLException {
        new UserDAO().delete(id);
    }

    public List listUsers() {
        UserDAO dao = new UserDAO();
        try {
            return dao.findAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, 
				"Problemas ao localizar usuário" + 
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
