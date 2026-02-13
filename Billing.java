package com.nit.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Billing {
	 	int id;
	    int patientId;
	    double totalAmount;
	    String paymentStatus;
	    LocalDate billDate;
}
