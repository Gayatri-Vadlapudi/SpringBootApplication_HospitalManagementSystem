package com.nit.repository;

import java.util.List;

import com.nit.model.Doctor;


public interface IDoctorRepository {
	public int addDoctor(Doctor doctor);
	public List<Doctor> getDoctorDetails();
	public Doctor getDoctorById(int id);	
}
