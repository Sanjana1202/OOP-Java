package fr.sanjana.test;

import fr.sanjana.services.InsuranceDAO;
import fr.sanjana.services.MedicationDAO;
import fr.sanjana.services.PatientDAO;
import fr.sanjana.services.PrescriptionDAO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;

public class TestJDB3 {
    public static void test() throws IOException, SQLException {
        String insuranceSql =  Files.readString(Path.of("resources/ create-insurances.sql"));
        InsuranceDAO insuranceDAO = new InsuranceDAO();
        insuranceDAO.connection.createStatement().execute(insuranceSql);

        String patientSql =  Files.readString(Path.of("resources/create-patients.sql"));
        PatientDAO patientDAO = new PatientDAO();
        patientDAO.getConnection().createStatement().execute(patientSql);

        String medicationSql =  Files.readString(Path.of("resources/create-medications.sql"));
        MedicationDAO medicationDAO = new MedicationDAO();
        medicationDAO.connection.createStatement().execute(medicationSql);

        String prescriptionSql =  Files.readString(Path.of("resources/create-prescriptions.sql"));
        PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
        prescriptionDAO.connection.createStatement().execute(prescriptionSql);
    }

    public static void main(String[] args) throws SQLException, IOException {
        TestJDB3.test();
    }
}
