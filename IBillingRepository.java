package com.nit.repository;

import java.util.List;

import com.nit.model.Billing;

public interface IBillingRepository {
	public double doctorConsultantFee(int appointmentId);
	
	public int generateBill(Billing billing);

	public List<Billing> findAll();
	
}
