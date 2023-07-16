package fr.sanjana.services;

import fr.sanjana.dataModels.Prescription;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionReader {
    public static List<Prescription> readAll() throws IOException {

        List<String> lines = Files.readAllLines(Path.of("resources/prescriptions.csv"));

        // Remove header
        lines.remove(0);


        List<Prescription> prescriptionList = new ArrayList<>();


        for (String s : lines) {
            String[] columns = s.split(";");
            int presc_id = Integer.parseInt(columns[0].trim());
            String ref = columns[1].trim();
            String code = columns[2].trim();
            String days = columns[3].trim();

            Prescription prescription = new Prescription(presc_id, ref, code, days);
            prescriptionList.add(prescription);

        }
        return prescriptionList;
    }
}
