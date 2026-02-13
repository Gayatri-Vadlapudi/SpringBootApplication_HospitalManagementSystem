package com.nit.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Appointment {
	int id;
	int patientId;
	int doctorId;
	LocalDate appointmentDate;
	String status;
}
