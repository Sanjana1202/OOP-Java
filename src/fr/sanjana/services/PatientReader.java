package fr.sanjana.services;

import fr.sanjana.dataModels.Patient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientReader {
    public static List<Patient> readAll () throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Path.of("resources/patients.csv"));
        lines.remove(0);
        // Empty list of patients
        List<Patient> patientslist = new ArrayList<>();
        // Fill list of patients
        for (String s : lines) {
            String[] columns = s.split(";");
            String pat_num_HC = columns[0].trim().replace("\"","");
            String pat_lastname = columns[1].trim();
            String pat_firstname = columns[2].trim();
            String pat_address = columns[3].trim();
            String pat_tel = columns[4].trim().replace("\"","");
            int pat_insurance_id = Integer.parseInt(columns[5].trim());
            Date pat_sub_date = new SimpleDateFormat("dd/MM/yyyy").parse(columns[6].trim());

            Patient patients = new Patient(pat_num_HC, pat_lastname, pat_firstname, pat_address, pat_tel, pat_insurance_id, pat_sub_date);
            patientslist.add(patients);
        }
        return patientslist;
    }
}


