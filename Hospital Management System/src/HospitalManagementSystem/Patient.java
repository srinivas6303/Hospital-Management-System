package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    // Connection interface and its object to connect to the database
    private Connection connection;

    // Scanner class and its object to take inputs from the user
    private Scanner scanner;

    // Parameterized constructor (passing values from the main class)
    public Patient(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }



    // addPatient
    public void addPatient() {
        System.out.println("Enter patient name:");
        String name = scanner.next();

        System.out.println("Enter patient age:");
        int age = scanner.nextInt();

        System.out.println("Enter patient gender:");
        String gender = scanner.next();

        String query = "INSERT INTO patients(name, age, gender) VALUES(?, ?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient Added Successfully!!");
            } else {
                System.out.println("Failed to add Patient!!");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePatient(int id) {
        String query = "DELETE FROM patients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deleted Patient by id=" + id);
            } else {
                System.out.println("Patient id not found!");
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // viewPatients
    public void viewPatients() {
        String query = "SELECT * FROM patients";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients:");
            System.out.println("+------------+--------------------+----------+------------+");
            System.out.println("| Patient Id |        Name        |    Age   | Gender     |");
            System.out.println("+------------+--------------------+----------+------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                System.out.printf("| %-10d | %-18s | %-8d | %-10s |\n", id, name, age, gender);
                System.out.println("+------------+--------------------+-----------+------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // getPatientById
    public boolean getPatientById(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



