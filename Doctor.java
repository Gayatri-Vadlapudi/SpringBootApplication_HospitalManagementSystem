package com.nit.model;

import lombok.Data;

@Data
public class Doctor {
	int id;
	String doctorName;
	String specialization;
	int experience;
	String phoneNumber;
}
