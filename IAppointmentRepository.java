package com.nit.repository;

import java.util.List;

import com.nit.model.Appointment;

public interface IAppointmentRepository {
	public int bookAppointment(Appointment appointment);
	public List<Appointment> getAppointmentDetails();
	public Appointment getAppointmentById(int id);
}
