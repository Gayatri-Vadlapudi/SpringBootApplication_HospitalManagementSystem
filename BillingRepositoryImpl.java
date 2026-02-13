package com.nit.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nit.model.Billing;
import java.util.Collections;
@Repository
public class BillingRepositoryImpl implements IBillingRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public double doctorConsultantFee(int appointmentId) {

	    String sql = "SELECT total_amount FROM bill WHERE appointment_id = ?";

	    try {

	        return jdbcTemplate.queryForObject(sql, Double.class, appointmentId);

	    } catch (EmptyResultDataAccessException e) {

	        System.out.println("No bill found for appointment ID: " + appointmentId);
	        return 0.0;

	    } catch (Exception e) {

	        System.err.println("Error fetching consultant fee: " + e.getMessage());
	        return 0.0;
	    }
	}


	

	@Override
	public int generateBill(Billing billing) {

	    String sql = "INSERT INTO bill "
	               + "(id, patient_id, total_amount, payment_status, bill_date) "
	               + "VALUES(billing_seq.NEXTVAL, ?, ?, ?, ?)";

	    try {

	        return jdbcTemplate.update(sql,
	                billing.getPatientId(),
	                billing.getTotalAmount(),
	                billing.getPaymentStatus(),
	                java.sql.Date.valueOf(billing.getBillDate()));
	               

	    } catch (Exception e) {

	        System.err.println("Error generating bill: " + e.getMessage());
	        return 0;
	    }
	}



	@Override
	public List<Billing> findAll() {

	    String sql = "SELECT * FROM bill";

	    try {

	        return jdbcTemplate.query(sql, (rs, rowNum) -> {

	            Billing billing = new Billing();

	            billing.setId(rs.getInt("id"));
	            billing.setPatientId(rs.getInt("patient_id"));
	            billing.setTotalAmount(rs.getDouble("total_amount"));
	            billing.setPaymentStatus(rs.getString("payment_status"));
	            billing.setBillDate(
	                    rs.getDate("bill_date").toLocalDate()
	            );

	            return billing;
	        });

	    } catch (Exception e) {

	        System.err.println("Error fetching billing details: " + e.getMessage());
	        return Collections.emptyList();
	    }
	}


}
