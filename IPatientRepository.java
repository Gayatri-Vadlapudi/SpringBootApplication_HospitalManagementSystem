package com.nit.repository;

import java.util.List;

import com.nit.model.Patient;

public interface IPatientRepository {
	public int addPatient(Patient patient);
	public List<Patient> getPatientDetails();
	public Patient getPatientById(int id);
	public int delete(int id);
}
