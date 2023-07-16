package fr.sanjana.services;

import fr.sanjana.dataModels.Insurance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class InsuranceReader {
    public static List<Insurance> readAll() throws IOException {

        List<String> lines = Files.readAllLines(Path.of("resources/insurances.csv"));

        // Remove header
        lines.remove(0);

        // Empty list of Insurance
        List<Insurance> insuranceList = new ArrayList<>();

        // Fill list of Insurnace
        for (String s : lines) {
            String[] columns = s.split(";");
            int insurnace_id = Integer.parseInt(columns[0].trim());
            String insurance_name = columns[1].trim();

            Insurance insurance = new Insurance(insurnace_id,insurance_name);
            insuranceList.add(insurance);

        }
        return insuranceList;
    }
}

