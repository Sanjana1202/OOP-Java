package fr.sanjana.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestJDB1 {

    public static void test() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:mem:exam:DB_CLOSE_DELAY=-1");
        PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS PATIENTS(ID INTEGER PRIMARY KEY AUTO_INCREMENT, HC_NUM VARCHAR, FIRSTNAME VARCHAR, LASTNAME VARCHAR, ADDRESS VARCHAR, TELEPHONE VARCHAR, INSURANCE_ID INTEGER, SUB_DATE VARCHAR)");
        statement.execute();
    }

    public static void main(String[] args) throws SQLException {
        TestJDB1.test();
    }
}
