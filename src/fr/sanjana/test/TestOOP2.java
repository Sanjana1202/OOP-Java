package fr.sanjana.test;

import fr.sanjana.dataModels.Insurance;
import fr.sanjana.dataModels.Patient;
import fr.sanjana.services.InsuranceReader;
import fr.sanjana.services.PatientReader;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class TestOOP2 {
    public static void test() throws IOException, ParseException {


        List<Patient> patientsList = PatientReader.readAll();
        System.out.println(patientsList);

        List<Insurance> insuranceList = InsuranceReader.readAll();
        System.out.println(insuranceList);
    }

    public static void main(String[] args) throws IOException, ParseException {
        TestOOP2.test();
    }
}
