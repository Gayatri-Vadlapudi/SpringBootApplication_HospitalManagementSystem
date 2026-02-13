package com.example.demo;

import java.time.LocalDate;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.nit.model.Appointment;
import com.nit.model.Doctor;
import com.nit.model.Patient;
import com.nit.service.AppointmentService;
import com.nit.service.BillingService;
import com.nit.service.DoctorService;
import com.nit.service.PatientService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demo", "com.nit"})
public class HospitalManagementProjectApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx=SpringApplication.run(HospitalManagementProjectApplication.class, args);
		
		Scanner sc=new Scanner(System.in);
		boolean exit=false;
		
		while(!exit) {
			System.out.println("\n ==== Hospital Management ====");
			System.out.println("1. Manage Patients");
			System.out.println("2. Manage Doctors");
			System.out.println("3. Manage Appointments");
			System.out.println("4. Manage Billing Accounts");
			System.out.println("5. Exit");
			System.out.println("Enter your choice::");
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1->{
				System.out.println("Managing Patient Details::");
				System.out.println("1. Add Patients");
				System.out.println("2. View All Patients Details");
				System.out.println("3. Get Patient");
				System.out.println("4. Delete Patient");
				System.out.println("5. Exit");
				System.out.println("Enter your choice about Patients Data::");
				PatientService patientService =ctx.getBean(PatientService.class);
				int operation=sc.nextInt();
				sc.nextLine();
				switch(operation){
				case 1->{
					System.out.println(":::: Adding Patient Details ::::");
					System.out.println("Enter Patient Name:");
					String pname=sc.nextLine();
					System.out.println("Enter Patient Age:");
					int age=sc.nextInt();sc.nextLine();
					System.out.println("Enter Patient Disease:");
					String disease=sc.nextLine();
					System.out.println("Enter Patient Gender:");
					String gender=sc.nextLine();
					System.out.println("Enter Patient PhoneNumber:");
					String phoneNumber=sc.nextLine();
					
					Patient patient=new Patient();
					patient.setPatientName(pname);
					patient.setAge(age);
					patient.setGender(gender);
					patient.setPhoneNumber(phoneNumber);
					patient.setAdmittedDate(LocalDate.now());
					
					
					patientService.addPatient(patient);
				}
				case 2->{
					System.out.println(":::: View All Patients ::::");
					System.out.println(patientService.getAllPatients());
					
				}
				case 3->{
					System.out.println(":::: View Particular Patient Details by providing Id ::::");
					System.out.println("Enter the patient Id:->");
					int patientId=sc.nextInt();
					System.out.println( patientService.getPatientById(patientId));
				}
				case 4->{
					System.out.println(":::: Delete Particular Patient Details by providing Id ::::");
					System.out.println("Enter the patient Id:->");
					int patientId=sc.nextInt();
					System.out.println(patientService.deletePatient(patientId));
				}
				default ->{
					System.out.println("Invalid Operation for Patients.Please choose the correct One!!");
				}
				}
			}
			case 2->{
				System.out.println("Managing Doctor Details::");
				System.out.println("1. Add Doctors");
				System.out.println("2. View All Doctors Details");
				System.out.println("3. Get Doctor");
				System.out.println("4.Exit");
				System.out.println("Enter your choice about Doctors Data::");
				DoctorService doctorService =ctx.getBean(DoctorService.class);
				int operation=sc.nextInt();
				sc.nextLine();
				switch(operation){
				case 1->{
					System.out.println(":::: Adding Patient Details ::::");
					System.out.println("Enter Doctor Name:");
					String dname=sc.nextLine();
					System.out.println("Enter Doctors Experience:");
					int exp=sc.nextInt();sc.nextLine();
					System.out.println("Enter Doctor Specialization:");
					String specialization=sc.nextLine();
					System.out.println("Enter Doctor phone Number:");
					String phone=sc.nextLine();
					
					Doctor doctor=new Doctor();
					doctor.setDoctorName(dname);
					doctor.setExperience(exp);
					doctor.setPhoneNumber(phone);
					doctor.setSpecialization(specialization);
					//adding doctor
					doctorService.addDoctor(doctor);
				}
				case 2->{
					System.out.println(":::: View All Doctors ::::");
					doctorService.getAllDoctors();
				}
				case 3->{
					System.out.println(":::: View Particular Doctor Details by providing Id ::::");
					System.out.println("Enter the Doctor Id:->");
					int doctorId=sc.nextInt();
					doctorService.getDoctorById(doctorId);
				}
				case 4->{
					System.out.println("Thank you! Visit Again!");
				}
				default ->{
					System.out.println("Invalid Operation for Patients.Please choose the correct One!!");
				}
				}
			}
			case 3->{
				System.out.println("Managing Appointment Details::");
				System.out.println("1. Book Appointment");
				System.out.println("2. Get All Appointment Details");
				System.out.println("3. Get Appointment by Id");
				System.out.println("4.Exit");
				System.out.println("Enter your choice about Appointments Data::");
				AppointmentService appointmentService =ctx.getBean(AppointmentService.class);
				int operation=sc.nextInt();
				sc.nextLine();
				switch(operation){
				case 1->{
					System.out.println(":::: Booking Appointment Details ::::");
					System.out.println("Enter Doctor Id:");
					int dId=sc.nextInt();
					System.out.println("Enter Patient Id:");
					int pId=sc.nextInt();sc.nextLine();
					System.out.println("Enter Booking Status:");
					String status=sc.nextLine();
					
					
					
					Appointment appointment=new Appointment();
					appointment.setPatientId(pId);
					appointment.setDoctorId(dId);
					appointment.setStatus(status);
					appointment.setAppointmentDate(LocalDate.now());
					//booking an appointment
					appointmentService.bookAppointment(appointment);
					
				}
				case 2->{
					System.out.println(":::: View All Doctors ::::");
					appointmentService.getAllAppointments();
				}
				case 3->{
					System.out.println(":::: View Particular Booking Details by providing Id ::::");
					System.out.println("Enter the Booking Id:->");
					int bookingId=sc.nextInt();
					appointmentService.getAppointmentById(bookingId);
				}
				case 4->{
					System.out.println("Thank you! Visit Again!");
				}
				default ->{
					System.out.println("Invalid Operation for Patients.Please choose the correct One!!");
				}
				}
			}
			
			
//			case 4->{
//			System.out.println("Managing Billing Details::");
//			System.out.println("1. Book Appointment");
//			System.out.println("2. Get All Appointment Details");
//			System.out.println("3. Get Appointment by Id");
//			System.out.println("4.Exit");
//			System.out.println("Enter your choice about Appointments Data::");
//			BillingService billingService =ctx.getBean(BillingService.class);
//			int operation=sc.nextInt();
//			sc.nextLine();
//			switch(operation){
//			case 1->{
//				System.out.println(":::: Billing Details ::::");
//				System.out.println("Enter Doctor Id:");
//				int dId=sc.nextInt();
//				System.out.println("Enter Patient Id:");
//				int pId=sc.nextInt();sc.nextLine();
//				
//				System.out.println("Enter Payment Status:");
//				String status=sc.nextLine();
//				System.out.println("Enter Total Amount:");
//				double totalAmount=sc.nextDouble();
//				
//				
//				
//				
//				billingService.generateBill(appointment);
//				
//			}
//			case 2->{
//				System.out.println(":::: View All Doctors ::::");
//				appointmentService.getAllAppointments();
//			}
//			case 3->{
//				System.out.println(":::: View Particular Booking Details by providing Id ::::");
//				System.out.println("Enter the Booking Id:->");
//				int bookingId=sc.nextInt();
//				appointmentService.getAppointmentById(bookingId);
//			}
//			case 4->{
//				System.out.println("Thank you! Visit Again!");
//			}
//			default ->{
//				System.out.println("Invalid Operation for Patients.Please choose the correct One!!");
//			}
//			}
//		}
			case 5->{
				exit=true;
				
			}
			}
			
		}
		}
}
