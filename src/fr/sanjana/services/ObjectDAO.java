package fr.sanjana.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class ObjectDAO<T> {
    public Connection connection;

    public ObjectDAO(String ddl) throws SQLException {
        this.connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(ddl);
        preparedStatement.execute();
    }

    protected Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:exam:DB_CLOSE_DELAY=-1");
    }

    public abstract void save(T t) throws SQLException;

    public abstract T search(int id) throws SQLException;

    public abstract void update(T t) throws SQLException;

    public abstract void delete(T t) throws SQLException;
}
