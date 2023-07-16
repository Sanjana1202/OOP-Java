package fr.sanjana.services;

import fr.sanjana.dataModels.Patient;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    private Connection connection;

    public PatientDAO() throws SQLException {
        this.connection = connection();
        ensureDDL();
    }

    public Connection getConnection() {
        return connection;
    }

    private Connection connection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:exam:DB_CLOSE_DELAY=-1");
        return connection;
    }

    public void ensureDDL() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS PATIENTS (HC_NUM VARCHAR PRIMARY KEY, FIRSTNAME VARCHAR, LASTNAME VARCHAR, ADDRESS VARCHAR, TELEPHONE VARCHAR, INSURANCE_ID INT, SUB_DATE DATE)");
        statement.execute();
    }

    public void save(Patient patients) throws SQLException {
        PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO PATIENTS(HC_NUM, FIRSTNAME, LASTNAME, ADDRESS, TELEPHONE, INSURANCE_ID, SUB_DATE) values (?, ?, ?, ?, ?, ?,?)");
        insertStatement.setString(1, patients.getPat_num_HC());
        insertStatement.setString(2, patients.getPat_firstname());
        insertStatement.setString(3, patients.getPat_lastname());
        insertStatement.setString(4, patients.getPat_address());
        insertStatement.setString(5, patients.getPat_tel());
        insertStatement.setInt(6, patients.getPat_insurance_id());
        java.util.Date utilDate = patients.getPat_sub_date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        insertStatement.setDate(7, sqlDate);
        insertStatement.execute();
    }

    public List<Patient> search() throws SQLException {
        List<Patient> passengerList = new ArrayList<>();
        PreparedStatement selectStatement = connection.prepareStatement("SELECT  hc_num, firstname, lastname, address, telephone, insurance_id, sub_date FROM PATIENTS");
        ResultSet resultSet = selectStatement.executeQuery();
        while (resultSet.next()) {
            String hc_num = resultSet.getString("hc_num");
            String first_name = resultSet.getString("firstname");
            String last_name = resultSet.getString("lastname");
            String address = resultSet.getString("address");
            String telephone = resultSet.getString("telephone");
            int insurance_id = resultSet.getInt("insurance_id");
            Date sub_date = resultSet.getDate("sub_date");
            Patient passenger = new Patient(hc_num, first_name, last_name, address, telephone, insurance_id, sub_date);
            passengerList.add(passenger);
        }
        return passengerList;
    }

    public Patient search(String firstname, String lastname) throws SQLException, ParseException {
        Patient patient = new Patient();
        String query = "SELECT  hc_num, firstname, lastname, address, telephone, insurance_id, sub_date FROM PATIENTS WHERE firstname=? AND lastname=?";
        PreparedStatement selectStatement = connection.prepareStatement(query);
        selectStatement.setString(1, firstname);
        selectStatement.setString(2, lastname);
        ResultSet resultSet = selectStatement.executeQuery();

        while (resultSet.next()) {
            String hc_num = resultSet.getString("hc_num");
            String first_name = resultSet.getString("firstname");
            String last_name = resultSet.getString("lastname");
            String address = resultSet.getString("address");
            String telephone = resultSet.getString("telephone");
            int insurance_id = resultSet.getInt("insurance_id");
            String sub_date = resultSet.getString("sub_date");
            patient.setPat_num_HC(hc_num);
            patient.setPat_firstname(first_name);
            patient.setPat_lastname(last_name);
            patient.setPat_address(address);
            patient.setPat_insurance_id(insurance_id);
            patient.setPat_tel(telephone);
            patient.setPat_sub_date(new SimpleDateFormat("yyyy-MM-dd").parse(sub_date));
        }

        return patient;
    }

    public Patient search(String hc_num) throws SQLException, ParseException {
        String query = "SELECT hc_num, firstname, lastname, address, telephone, insurance_id, sub_date FROM PATIENTS WHERE HC_NUM=?";
        PreparedStatement selectStatement = connection.prepareStatement(query);

        selectStatement.setString(1, hc_num);
        ResultSet resultSet = selectStatement.executeQuery();

        Patient patient = new Patient();
        while (resultSet.next()) {
            String hcnum = resultSet.getString("hc_num");
            String first_name = resultSet.getString("firstname");
            String last_name = resultSet.getString("lastname");
            String address = resultSet.getString("address");
            String telephone = resultSet.getString("telephone");
            int insurance_id = resultSet.getInt("insurance_id");
            String sub_date = resultSet.getString("sub_date");
            patient.setPat_num_HC(hcnum);
            patient.setPat_address(address);
            patient.setPat_firstname(first_name);
            patient.setPat_lastname(last_name);
            patient.setPat_tel(telephone);
            patient.setPat_insurance_id(insurance_id);
            patient.setPat_sub_date(new SimpleDateFormat("yyyy-MM-dd").parse(sub_date));
        }
        return patient;
    }



    public void update(Patient patients) throws SQLException {
        String query = "UPDATE PATIENTS SET firstname=?, lastname=?, address=?, telephone=?, insurance_id=?, sub_date=? WHERE HC_NUM = ?";
        PreparedStatement updateStatement = connection.prepareStatement(query);
        updateStatement.setString(1, patients.getPat_firstname());
        updateStatement.setString(2, patients.getPat_lastname());
        updateStatement.setString(3, patients.getPat_address());
        updateStatement.setString(4, patients.getPat_tel());
        updateStatement.setInt(5, patients.getPat_insurance_id());
        updateStatement.setDate(6, new java.sql.Date(patients.getPat_sub_date().getTime()));
        updateStatement.setString(7, patients.getPat_num_HC());
        updateStatement.executeUpdate();
    }

    public void delete(Patient patients) throws SQLException {
        String query = "DELETE FROM PATIENTS WHERE HC_NUM= ?";
        PreparedStatement deleteStatement = connection.prepareStatement(query);
        deleteStatement.setString(1, patients.getPat_num_HC());
        deleteStatement.execute();
    }


}
