package com.nit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.model.Appointment;
import com.nit.model.Billing;
import com.nit.repository.IBillingRepository;

@Service
public class BillingService {

    @Autowired
    private IBillingRepository repository;

    public int generateBill(Appointment appointment) {

        double fee = repository.doctorConsultantFee(appointment.getDoctorId());

        if (fee <= 0) {
            throw new RuntimeException("Invalid Bill Amount");
        }

        Billing billing = new Billing();
        billing.setPatientId(appointment.getPatientId());
        billing.setTotalAmount(fee);
        billing.setPaymentStatus("PENDING");

        return repository.generateBill(billing);
    }

    public List<Billing> getAllBills() {
        return repository.findAll();
    }

}

