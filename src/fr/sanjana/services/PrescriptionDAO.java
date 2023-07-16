package fr.sanjana.services;

import fr.sanjana.dataModels.Prescription;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrescriptionDAO extends ObjectDAO<Prescription> {
    public PrescriptionDAO() throws SQLException {
        super("CREATE TABLE IF NOT EXISTS PRESCRIPTIONS(PRESC_ID INTEGER PRIMARY KEY AUTO_INCREMENT, REF VARCHAR, CODE INTEGER, DAYS VARCHAR)");
    }


    @Override
    public void save(Prescription prescription) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO PRESCRIPTIONS(presc_id, ref, code, days) values (?, ?, ?,?)");
        insertStatement.setInt(1, prescription.getPresc_id());
        insertStatement.setString(2, prescription.getPresc_code());
        insertStatement.setString(3, prescription.getPresc_code());
        insertStatement.setString(4, prescription.getPresc_days());
        insertStatement.execute();
    }

    public Prescription search(int id) throws SQLException {
        String query = "SELECT PRESC_ID, REF, CODE, DAYS FROM PRESCRIPTIONS WHERE PRESC_ID=?";
        PreparedStatement selectStatement = connection.prepareStatement(query);
        selectStatement.setInt(1, id);

        ResultSet resultSet = selectStatement.executeQuery();

        Prescription prescription = new Prescription();
        while (resultSet.next()) {
            int presc_id = resultSet.getInt("presc_id");
            String ref = resultSet.getString("ref");
            String code = resultSet.getString("code");
            String days = resultSet.getString("days");

            prescription.setPresc_id(presc_id);
            prescription.setPresc_code(code);
            prescription.setPresc_ref_pat(ref);
            prescription.setPresc_days(days);
        }

        return prescription;
    }

    @Override
    public void update(Prescription prescription) throws SQLException {
        String query = "UPDATE PRESCRIPTIONS SET CODE=?,REF=?,DAYS=? WHERE PRESC_ID = ?";
        PreparedStatement updateStatement = connection.prepareStatement(query);
        updateStatement.setString(1, prescription.getPresc_code());
        updateStatement.setString(2, prescription.getPresc_ref_pat());
        updateStatement.setString(3, prescription.getPresc_days());
        updateStatement.setInt(4, prescription.getPresc_id());
        updateStatement.executeUpdate();
    }

    @Override
    public void delete(Prescription prescription) throws SQLException {
        String query = "DELETE FROM PRESCRIPTIONS WHERE PRESC_ID= ?";
        PreparedStatement deleteStatement = connection.prepareStatement(query);
        deleteStatement.setInt(1, prescription.getPresc_id());
        deleteStatement.execute();
    }
}

