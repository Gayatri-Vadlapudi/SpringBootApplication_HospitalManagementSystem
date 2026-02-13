package com.nit.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.model.Appointment;
import com.nit.repository.IAppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private IAppointmentRepository repository;

    public int bookAppointment(Appointment appointment) {

        if (appointment.getAppointmentDate() == null) {
            throw new RuntimeException("Appointment date required");
        }

        return repository.bookAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return repository.getAppointmentDetails();
    }
    public Appointment getAppointmentById(int id) {
    		return repository.getAppointmentById(id);
    }
}
