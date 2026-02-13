package com.nit.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import com.nit.model.Patient;
@Repository
public class PatientRepositaryImpl implements IPatientRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int addPatient(Patient patient) {

	    String sql = "INSERT INTO patients(id, name, age, gender, phone, disease, admitted_date) "
	               + "VALUES(patients_seq.NEXTVAL, ?, ?, ?, ?, ?, ?)";

	    return jdbcTemplate.update(sql,
	            patient.getPatientName(),
	            patient.getAge(),
	            patient.getGender(),
	            patient.getPhoneNumber(),
	            patient.getDisease(),
	            patient.getAdmittedDate());
	}
	@Override
	public List<Patient> getPatientDetails() {

	    String sql = "SELECT * FROM patients";

	    try {

	        return jdbcTemplate.query(sql, (rs, rowNum) -> {

	            Patient patient = new Patient();

	            patient.setId(rs.getInt("id"));
	            patient.setPatientName(rs.getString("name"));
	            patient.setAge(rs.getInt("age"));
	            patient.setGender(rs.getString("gender"));
	            patient.setPhoneNumber(rs.getString("phone"));
	            patient.setDisease(rs.getString("disease"));
	            patient.setAdmittedDate(
	                    rs.getDate("admitted_date").toLocalDate()
	            );

	            return patient;
	        });

	    } catch (Exception e) {

	        System.out.println("Error while fetching patient details: " + e.getMessage());
	        e.printStackTrace();
	        return null;   // or return Collections.emptyList();
	    }
	}

	

	@Override
	public Patient getPatientById(int id) {

	    String sql = "SELECT * FROM patients WHERE id = ?";

	    try {

	        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

	            Patient patient = new Patient();

	            patient.setId(rs.getInt("id"));
	            patient.setPatientName(rs.getString("name"));
	            patient.setAge(rs.getInt("age"));
	            patient.setGender(rs.getString("gender"));
	            patient.setPhoneNumber(rs.getString("phone"));
	            patient.setDisease(rs.getString("disease"));
	            patient.setAdmittedDate(
	                    rs.getDate("admitted_date").toLocalDate()
	            );

	            return patient;

	        }, id);

	    } catch (EmptyResultDataAccessException e) {

	        System.out.println("No patient found with ID: " + id);
	        return null;

	    } catch (Exception e) {

	        System.out.println("Database error: " + e.getMessage());
	        return null;
	    }
	}


	@Override
	public int delete(int id) {

	    String sql = "DELETE FROM patient WHERE id = ?";

	    try {

	        return jdbcTemplate.update(sql, id);

	    } catch (Exception e) {

	        System.err.println("Error deleting patient with ID " + id + ": " + e.getMessage());
	        return 0;
	    }
	}


}
