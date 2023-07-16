package fr.sanjana.test;

import fr.sanjana.dataModels.Patient;
import fr.sanjana.services.PatientBLService;
import fr.sanjana.services.PatientReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class TestBLI1 {
    public static void test() throws IOException, ParseException {
        List<Patient> patientsList = PatientReader.readAll();
        Patient patient = patientsList.get(5);
        System.out.println(patient.getPat_firstname() + " " + patient.getPat_lastname() + " has seniority of " + PatientBLService.computeSeniority(patient));
    }

    public static void main(String[] args) throws IOException, ParseException {
        TestBLI1.test();
    }
}
