package Controllers;

import Models.DAO.ScoreDAO;
import Models.Entity.User;
import Models.Entity.Score;
import Models.Exceptions.InvalidEntityException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.swing.JOptionPane;

public class ScoreController {
    public void register(int value, User user) throws SQLException, ParseException, InvalidEntityException {
        if (value < 0) {
            throw new InvalidEntityException("Score value cannot be negative.");
        }

        if (user == null) {
            throw new InvalidEntityException("User cannot be null.");
        }

        Score score = new Score();
        score.setValue(value);
        score.setIdUser(user);

        new ScoreDAO().save(score);
    }

    public void alter(long id, int value, User user) throws ParseException, SQLException, InvalidEntityException {
        if (value < 0) {
            throw new InvalidEntityException("Score value cannot be negative.");
        }

        if (user == null) {
            throw new InvalidEntityException("User cannot be null.");
        }

        Score score = new Score();
        score.setId(id);
        score.setValue(value);
        score.setIdUser(user);

        new ScoreDAO().update(score);
    }

    public void delete(long id) throws SQLException {
        new ScoreDAO().delete(id);  
    }

    public List<Score> listScores() {
        ScoreDAO dao = new ScoreDAO();
        try {
            return dao.findAllOrderedByValue();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Problemas ao localizar score: " + e.getLocalizedMessage()
            );
        }
        return null;
    }
}
