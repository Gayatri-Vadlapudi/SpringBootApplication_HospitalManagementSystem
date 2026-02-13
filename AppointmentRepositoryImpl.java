package com.nit.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import com.nit.model.Appointment;
import org.springframework.dao.EmptyResultDataAccessException;

@Repository
public class AppointmentRepositoryImpl implements IAppointmentRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int bookAppointment(Appointment appointment) {

	    String sql = "INSERT INTO appointment "
	               + "(id, patient_id, doctor_id, appointment_date, status) "
	               + "VALUES(appointment_seq.NEXTVAL, ?, ?, ?, ?)";

	    return jdbcTemplate.update(sql,
	            appointment.getPatientId(),
	            appointment.getDoctorId(),
	            java.sql.Date.valueOf(appointment.getAppointmentDate()),
	            appointment.getStatus());
	}

	@Override
	public List<Appointment> getAppointmentDetails() {

	    String sql = "SELECT * FROM appointment";

	    try {

	        return jdbcTemplate.query(sql, (rs, rowNum) -> {

	            Appointment appointment = new Appointment();

	            appointment.setId(rs.getInt("id"));
	            appointment.setPatientId(rs.getInt("patient_id"));
	            appointment.setDoctorId(rs.getInt("doctor_id"));
	            appointment.setAppointmentDate(
	                    rs.getDate("appointment_date").toLocalDate()
	            );
	            appointment.setStatus(rs.getString("status"));

	            return appointment;
	        });

	    } catch (Exception e) {

	        System.err.println("Error fetching appointment details: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}


	
	@Override
	public Appointment getAppointmentById(int id) {

	    String sql = "SELECT * FROM appointment WHERE id = ?";

	    try {

	        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

	            Appointment appointment = new Appointment();

	            appointment.setId(rs.getInt("id"));
	            appointment.setPatientId(rs.getInt("patient_id"));
	            appointment.setDoctorId(rs.getInt("doctor_id"));
	            appointment.setAppointmentDate(
	                    rs.getDate("appointment_date").toLocalDate()
	            );
	            appointment.setStatus(rs.getString("status"));

	            return appointment;

	        }, id);

	    } catch (EmptyResultDataAccessException e) {

	        System.out.println("No appointment found with ID: " + id);
	        return null;

	    } catch (Exception e) {

	        System.err.println("Error fetching appointment with ID " + id + ": " + e.getMessage());
	        return null;
	    }
	}


}
