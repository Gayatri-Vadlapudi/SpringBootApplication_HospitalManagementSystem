package com.nit.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Collections;
import com.nit.model.Doctor;
@Repository
public class DoctorRepositoryImpl implements IDoctorRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int addDoctor(Doctor doctor) {
		
		String sql = "INSERT INTO doctors "
	               + "(id, name, specialization, experience, phone) "
	               + "VALUES(doctors_seq.NEXTVAL, ?, ?, ?, ?)";
		
	    return jdbcTemplate.update(sql,
	            doctor.getDoctorName(),
	            doctor.getSpecialization(),
	            doctor.getExperience(),
	            doctor.getPhoneNumber());
	        
	}


	@Override
	public List<Doctor> getDoctorDetails() {

	    String sql = "SELECT * FROM doctors";

	    try {

	        return jdbcTemplate.query(sql, (rs, rowNum) -> {

	            Doctor doctor = new Doctor();

	            doctor.setId(rs.getInt("id"));
	            doctor.setDoctorName(rs.getString("name"));
	            doctor.setSpecialization(rs.getString("specialization"));
	            doctor.setExperience(rs.getInt("experience"));
	            doctor.setPhoneNumber(rs.getString("phone"));

	            return doctor;
	        });

	    } catch (Exception e) {

	        System.err.println("Error fetching doctor details: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}

	

	@Override
	public Doctor getDoctorById(int id) {

	    String sql = "SELECT * FROM doctors WHERE id = ?";

	    try {

	        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {

	            Doctor doctor = new Doctor();

	            doctor.setId(rs.getInt("id"));
	            doctor.setDoctorName(rs.getString("name"));
	            doctor.setSpecialization(rs.getString("specialization"));
	            doctor.setExperience(rs.getInt("experience"));
	            doctor.setPhoneNumber(rs.getString("phone"));

	            return doctor;

	        }, id);

	    } catch (EmptyResultDataAccessException e) {

	        System.out.println("No doctor found with ID: " + id);
	        return null;

	    } catch (Exception e) {

	        System.err.println("Error fetching doctor with ID " + id + ": " + e.getMessage());
	        return null;
	    }
	}


}
