package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class GenericDAO<T> {
    protected Connection getConnection() {
        return DBConnection.getMyConnection();
    }

    public abstract void save(T entity) throws SQLException;

    public abstract void update(T entity) throws SQLException;

    public abstract void delete(long id) throws SQLException;

    public abstract T findById(long id) throws SQLException;

    public abstract List<T> findAll() throws SQLException;
}
