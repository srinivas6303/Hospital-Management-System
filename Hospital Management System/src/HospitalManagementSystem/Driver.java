package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Driver {
    private static final String url="jdbc:mysql://localhost:3306/hospital";
    private static final String username="root";
    private static final String password="Srinu@4848";
    private static final String driver="com.mysql.cj.jdbc.Driver8";

    public static void main(String args[]){
       /* try{
            Class.forName(driver);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }*/

        Scanner scanner=new Scanner(System.in);

        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Patient patient=new Patient(connection,scanner);
            Doctor doctor=new Doctor(connection);
            Appointment appointment=new Appointment(connection,scanner);

            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. Remove Patient");
                System.out.println("3. View Patients");
                System.out.println("4. View Doctors");
                System.out.println("5. Book Appointment");
                System.out.println("6. view Appointment");
                System.out.println("7. remove Appointment");
                System.out.println("8. Exit");

                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice){
                    case 1://addPatient
                        patient.addPatient();
                        System.out.println();
                        break;

                    case 2://removePatient
                        System.out.println("Enter patient ID to remove:");
                        int id=scanner.nextInt();

                        if(patient.getPatientById(id)) {
                            patient.removePatient(id);
                        }
                        else{
                            System.out.println("Patient ID not found.");
                        }
                        System.out.println();
                        break;


                    case 3://viewPatient
                        patient.viewPatients();
                        System.out.println();
                        break;

                    case 4://viewDcotors
                        doctor.viewDoctors();
                        System.out.println();
                        break;

                    case 5://bookAppointment
                        appointment.bookAppointment();
                        System.out.println();
                        break;


                    case 6://viewAppointments
                        appointment.viewAppointments();
                        System.out.println();
                        break;

                    case 7://remove Appointment66
                        appointment.removeAppointment();
                        System.out.println();
                        break;

                    case 8://exit
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

}
