package com.nit.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Patient {
	String patientName;
	int age;
	int id;
	String gender;
	String phoneNumber;
	String disease;
	LocalDate admittedDate;
	
}
