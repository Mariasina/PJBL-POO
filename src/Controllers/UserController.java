package Controllers;

import Models.DAO.UserDAO;
import Models.Entity.User;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

public class UserController {
    public void register(String username) throws SQLException, ParseException {
        User User = new User();
        User.setUsername(username);

        new UserDAO().registerUser(User);
    }

    public void alter(long id, String username) throws ParseException, SQLException {
		User User = new User();
        User.setId(id);
        User.setUsername(username);

        new UserDAO().alterUser(User);
    }

    public void delete(long id) throws SQLException {
        new UserDAO().deleteUser(id);
    }

    public List listUsers() {
        UserDAO dao = new UserDAO();
        try {
            return dao.findUsers();
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
