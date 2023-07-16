package fr.sanjana.dataModels;

public class Insurance {
        private int insurance_id;
        private String insurance_name;

        public Insurance() {
        }

        public Insurance(int insurance_int, String insurance_name) {
            this.insurance_id = insurance_int;
            this.insurance_name = insurance_name;
        }

        public int getInsurance_id() {
            return insurance_id;
        }

        public void setInsurance_id(int insurance_id) {
            this.insurance_id = insurance_id;
        }

        public String getInsurance_name() {
            return insurance_name;
        }

        public void setInsurance_name(String insurance_name) {
            this.insurance_name = insurance_name;
        }

        @Override
        public String toString() {
            return "Insurances{" +
                    "insurance_id=" + insurance_id +
                    ", insurance_name='" + insurance_name + '\'' +
                    '}';
        }
}


