package Models.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.GenericDAO;
import Models.Entity.Score;
import Models.Entity.User;

public class ScoreDAO extends GenericDAO<Score> {

    private UserDAO userDAO;

    public ScoreDAO() {
        this.userDAO = new UserDAO();
    }

    @Override
    public void save(Score score) throws SQLException {
        String sql = "INSERT INTO Score (score_value, id_user) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, score.getValue());
            pstmt.setLong(2, score.getIdUser().getId());
            pstmt.execute();
        }
    }

    @Override
    public void update(Score score) throws SQLException {
        String sql = "UPDATE Score SET score_value = ?, id_user = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, score.getValue());
            pstmt.setLong(2, score.getIdUser().getId());
            pstmt.setLong(3, score.getId());
            pstmt.execute();
        }
    }

    @Override
    public void delete(long id) throws SQLException {
        String sql = "DELETE FROM Score WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, id);
            pstmt.execute();
        }
    }

    @Override
    public Score findById(long id) throws SQLException {
        String sql = "SELECT * FROM Score WHERE id = ?";
        Score score = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    score = new Score();
                    score.setId(rs.getLong("id"));
                    score.setValue(rs.getInt("score_value"));

                    // Use the UserDAO to get the User object by user ID
                    User user = userDAO.findById(rs.getLong("id_user"));
                    score.setIdUser(user);
                }
            }
        }

        return score;
    }

    @Override
    public List<Score> findAll() throws SQLException {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM Score";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Score score = new Score();
                score.setId(rs.getLong("id"));
                score.setValue(rs.getInt("score_value"));

                // Use the UserDAO to get the User object by user ID
                User user = userDAO.findById(rs.getLong("id_user"));
                score.setIdUser(user);

                scores.add(score);
            }
        }

        return scores;
    }

    public List<Score> findAllOrderedByValue() throws SQLException {
        List<Score> scores = new ArrayList<>();
        String sql = "SELECT * FROM Score ORDER BY score_value DESC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Score score = new Score();
                score.setId(rs.getLong("id"));
                score.setValue(rs.getInt("score_value"));

                // Use the UserDAO to get the User object by user ID
                User user = userDAO.findById(rs.getLong("id_user"));
                score.setIdUser(user);

                scores.add(score);
            }
        }

        return scores;
    }
}
