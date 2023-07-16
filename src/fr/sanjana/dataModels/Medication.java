package fr.sanjana.dataModels;

public class Medication {
    private int medication_code;
    private String medication_name;
    private String medication_comment;

    public Medication() {
    }

    public Medication(int medication_code, String medication_name, String medication_comment) {
        this.medication_code = medication_code;
        this.medication_name = medication_name;
        this.medication_comment = medication_comment;
    }

    public int getMedication_code() {
        return medication_code;
    }

    public void setMedication_code(int medication_code) {
        this.medication_code = medication_code;
    }

    public String getMedication_name() {
        return medication_name;
    }

    public void setMedication_name(String medication_name) {
        this.medication_name = medication_name;
    }

    public String getMedication_comment() {
        return medication_comment;
    }

    public void setMedication_comment(String medication_comment) {
        this.medication_comment = medication_comment;
    }
}


