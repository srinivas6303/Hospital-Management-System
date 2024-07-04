package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Appointment {
    private Connection connection;
    private Scanner scanner;

    public Appointment(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }

    public void bookAppointment(){
        System.out.println("Enter Patient id:");
        int patientId=scanner.nextInt();

        System.out.println("Enter Doctor id:");
        int doctorId=scanner.nextInt();

        System.out.print("Enter appointment date (YYYY-MM-DD):");
        String date=scanner.next();

        if(isDoctorAvalible(doctorId,date)) {
            String query = "INSERT INTO appointments(patient_id, doctor_id, appointmentdate) VALUES(?, ?, ?)";


            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, patientId);
                preparedStatement.setInt(2, doctorId);
                preparedStatement.setString(3, date);

                int affectedrows = preparedStatement.executeUpdate();

                if (affectedrows > 0) {
                    System.out.println("Appointment Added Successfully!!");

                } else {
                    System.out.println("Failed to add Appointment!!");
                }
                preparedStatement.close();

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        else{
            System.out.println("The doctor is not available on this date. Please choose a different date.");
        }

    }


    public boolean isDoctorAvalible(int doctorId,String date){
        try{
            String query="select count(*) as count from appointments where doctor_id=? AND appointmentdate=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,doctorId);
            preparedStatement.setString(2,date);
            ResultSet resultSet= preparedStatement.executeQuery();

            if(resultSet.next()){

                int count=resultSet.getInt(1);
                if(count==0){
                    return true;
                }

            }
            else{
                return false;
            }



        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }





    public void viewAppointments(){
        String query="select * from appointments";

        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            ResultSet resultSet=preparedStatement.executeQuery();

            System.out.println("Appointments:");
            System.out.println("+---------------+------------+------------+-----------------+");
            System.out.println("| Appointment Id| Patient Id | Doctor Id  | Appointment Date|");
            System.out.println("+---------------+------------+------------+-----------------+");

            while(resultSet.next()){

                int id = resultSet.getInt(1);
                int patientId = resultSet.getInt(2);
                int doctorId = resultSet.getInt(3);
                String appointmentDate = resultSet.getString(4);
                System.out.printf("| %-13d | %-10d | %-10d | %-15s |\n", id, patientId, doctorId, appointmentDate);
                System.out.println("+---------------+------------+------------+-----------------+");
            }
            preparedStatement.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void removeAppointment(){
        System.out.println("Enter appointment ID to remove:");
        int id=scanner.nextInt();
        String query="delete from appointments where id=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            int rowsAffected=preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deleted Appointment by id=" + id);
            } else {
                System.out.println("Appointment id not found!");
            }
            preparedStatement.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }

    }

}
