package fr.sanjana.services;

import fr.sanjana.dataModels.Insurance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuranceDAO extends ObjectDAO<Insurance> {
    public InsuranceDAO() throws SQLException {
        super("CREATE TABLE IF NOT EXISTS INSURANCE(ID INTEGER PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR)");
    }

    @Override
    public void save(Insurance insurance) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO INSURANCE(ID,NAME) values (?, ?)");
        insertStatement.setInt(1, insurance.getInsurance_id());
        insertStatement.setString(2, insurance.getInsurance_name());
        insertStatement.execute();

    }

    public Insurance search(int id) throws SQLException {
        String query = "SELECT ID, NAME FROM INSURANCE WHERE ID = ?";
        PreparedStatement selectStatement = connection.prepareStatement(query);
        selectStatement.setInt(1, id);

        Insurance insurance = new Insurance();
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            int insurance_id = resultSet.getInt("id");
            String name = resultSet.getString("name");

            insurance.setInsurance_id(insurance_id);
            insurance.setInsurance_name(name);
        }

        return insurance;
    }

    @Override
    public void update(Insurance insurance) throws SQLException {
        String query = "UPDATE INSURANCE SET ID=?,NAME=? WHERE ID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(query);
        updateStatement.setInt(1, insurance.getInsurance_id());
        updateStatement.setString(2, insurance.getInsurance_name());
        updateStatement.executeUpdate();
    }

    @Override
    public void delete(Insurance insurance) throws SQLException {
        String query = "DELETE FROM INSURANCE WHERE ID= ?";
        PreparedStatement deleteStatement = connection.prepareStatement(query);
        deleteStatement.setInt(1, insurance.getInsurance_id());
        deleteStatement.execute();
    }
}
