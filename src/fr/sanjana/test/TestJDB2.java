package fr.sanjana.test;

import fr.sanjana.dataModels.Patient;
import fr.sanjana.services.PatientDAO;
import fr.sanjana.services.PatientReader;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class TestJDB2 {
    public static void test() throws IOException, ParseException, SQLException {
        //Initialise PatientDAO class
        PatientDAO connection = new PatientDAO();
        //Creating the patients table
        List<Patient> patientsList = PatientReader.readAll();
        connection.ensureDDL();
        //Inserting PatientList from Patient Reader class into the Database
        for (Patient p : patientsList) {
            connection.save(p);
        }
        //Search using first and last name
        Patient patient = connection.search("Bernard","Martin");
        System.out.println(patient);
        //updating the instance
        patient.setPat_firstname("Sanjana");
        connection.update(patient);
        System.out.println("UPDATING FIRSTNAME TO \"SANJANA\":");
        System.out.println(patient);
        //deleting the instance
        connection.delete(patient);
        System.out.println("AFTER DELETING");
        List<Patient> patients = connection.search();
        System.out.println(patients);
    }

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        TestJDB2.test();
    }

}
