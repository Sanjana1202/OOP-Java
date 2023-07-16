package fr.sanjana.services;

import fr.sanjana.dataModels.Patient;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientBLService {
    public static Integer computeSeniority(Patient patient) {
        Date subscriptionDate = patient.getPat_sub_date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        CharSequence subscriptionDateString = dateFormat.format(subscriptionDate);
        LocalDate parsedDate = LocalDate.parse(subscriptionDateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(parsedDate, currentDate);

        return period.getYears();
    }

    public static Map<String, Integer> computeSeniorityByPatient(List<Patient> patients) {
        Map<String, Integer> map = new HashMap<>();
        for (Patient p : patients) {
            int seniority = computeSeniority(p);
            String num_id = p.getPat_num_HC().replace("\"", "");
            map.put(num_id, seniority);
        }
        return map;

    }
}





