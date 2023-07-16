package fr.sanjana.services;

import fr.sanjana.dataModels.Medication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicationDAO extends ObjectDAO<Medication> {
    public MedicationDAO() throws SQLException {
        super("CREATE TABLE IF NOT EXISTS MEDICATIONS(CODE INTEGER PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR, COMMENT VARCHAR)");
    }

    @Override
    public void save(Medication medication) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO MEDICATIONS(NAME, COMMENT) values ( ?, ?)");

        insertStatement.setString(1, medication.getMedication_name());
        insertStatement.setString(2, medication.getMedication_comment());
        insertStatement.execute();
    }

    public Medication search(int id) throws SQLException {
        Medication medication = new Medication();
        String query = "SELECT code, name, comment FROM MEDICATIONS WHERE CODE=?";
        PreparedStatement selectStatement = connection.prepareStatement(query);
        selectStatement.setInt(1, id);
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            int code = resultSet.getInt("code");
            String name = resultSet.getString("name");
            String comment = resultSet.getString("comment");

            medication.setMedication_code(code);
            medication.setMedication_name(name);
            medication.setMedication_comment(comment);
        }

        return medication;
    }

    @Override
    public void update(Medication medication) throws SQLException {
        String query = "UPDATE MEDICATIONS SET CODE =?, NAME = ?,CODE=? WHERE CODE=?";
        PreparedStatement updateStatement = connection.prepareStatement(query);
        updateStatement.setInt(1, medication.getMedication_code());
        updateStatement.setString(2, medication.getMedication_name());
        updateStatement.setString(3, medication.getMedication_comment());
        updateStatement.setInt(1,medication.getMedication_code());
        updateStatement.executeUpdate();

    }

    @Override
    public void delete(Medication medication) throws SQLException {
        String query = "DELETE FROM MEDICATIONS WHERE CODE= ?";
        PreparedStatement deleteStatement = connection.prepareStatement(query);
        deleteStatement.setInt(1, medication.getMedication_code());
        deleteStatement.execute();
    }
}

