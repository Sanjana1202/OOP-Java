package fr.sanjana.test;

import fr.sanjana.dataModels.Medication;
import fr.sanjana.dataModels.Patient;
import fr.sanjana.dataModels.Prescription;
import fr.sanjana.services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public class TestJDB4 {

    public static void displayingPrescriptions() throws IOException, SQLException, ParseException {
        List<Prescription> prescriptionList = PrescriptionReader.readAll();
        List<Patient> patientList = PatientReader.readAll();
        PatientDAO patientDAO = new PatientDAO();
        for (Patient p : patientList) {
            patientDAO.save(p);
        }

        MedicationDAO medicationDAO = new MedicationDAO();


        for (Prescription prescription : prescriptionList) {
            String ref_id = prescription.getPresc_ref_pat();
            Patient patient = patientDAO.search(ref_id);
            String patientFirstname = patient.getPat_firstname();

            List<Medication> medicationList = MedicationReader.readAll();
            for (Medication m : medicationList) {
                medicationDAO.save(m);
            }

            int medicationId = Integer.parseInt(prescription.getPresc_code());
            Medication medication = medicationDAO.search(medicationId);
            String medicationName = medication.getMedication_name();

            System.out.println(patientFirstname + "\n" + "medication given: " + medicationName + "\n" +
                    "No of days prescription valid for: " + prescription.getPresc_days() + "\n");
        }
    }

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        TestJDB4.displayingPrescriptions();
    }

}