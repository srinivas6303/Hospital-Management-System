Hospital Management System
---------------------------
Skills Used:
-------------
Java
SQL
JDBC

Description:
-------------
Developed a Java-based system for managing hospital operations, including patients, doctors, and appointments.

Patient Class: Methods for adding, removing, viewing, and retrieving patient details.
Doctor Class: Methods for checking doctor availability and viewing doctor details.
Appointment Class: Methods for booking appointments and viewing appointment details.
Driver Class: Acts as the main driver, featuring a user-friendly menu and managing database interactions.

Project Structure:
------------------
1. Patient:
Methods	Return
addPatient()	void
viewPatients()	void
getPatientById()	boolean

2. Doctor:
Methods	Return
viewDoctors()	void
getDoctorById()	boolean


4. Appointment:
Methods	Return
bookAppointment()	void
isDoctorAvailable()	boolean
viewAppointments()	void


6. Driver:
Acts as the driver and connection manager.
Runs Patient, Doctor, and Appointment classes.
Contains the main menu.


Database Schema:
----------------
Database Name: HOSPITAL

Tables-1:
--------
patients: Stores information about patients.
Fields: id (int), name (varchar), age (int), gender (varchar).

Tables-2:
--------
doctors: Stores information about doctors.
Fields: id (int), name (varchar), specialization (varchar).

Tables-3:
--------
appointments: Stores information about appointments.
Fields: id (int), patient_id (int, references patients.id), doctor_id (int, references doctors.id), appointmentdate (date).
