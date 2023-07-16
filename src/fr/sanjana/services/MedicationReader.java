package fr.sanjana.services;

import fr.sanjana.dataModels.Medication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MedicationReader {
    public static List<Medication> readAll () throws IOException {

        List<String> lines = Files.readAllLines(Path.of("resources/medications.csv"));

        lines.remove(0);


        List<Medication> medicationsList = new ArrayList<>();

        for (String s : lines) {
            String[] columns = s.split(";");
            int med_code = Integer.parseInt(columns[0].trim());
            String med_name = columns[1].trim();
            String med_comment = columns[2].trim();

            Medication medication = new Medication(med_code,med_name,med_comment);
            medicationsList.add(medication);
        }
        return medicationsList;
    }
}

