package fr.sanjana.test;

import fr.sanjana.dataModels.Patient;
import fr.sanjana.services.PatientBLService;
import fr.sanjana.services.PatientReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class TestBLI2 {
    public static void test() throws IOException, ParseException {
        List<Patient> patientsList = PatientReader.readAll();
        Map<String, Integer> map = PatientBLService.computeSeniorityByPatient(patientsList);
        System.out.println(map);
    }

    public static void main(String[] args) throws IOException, ParseException {
        TestBLI2.test();
    }
}
