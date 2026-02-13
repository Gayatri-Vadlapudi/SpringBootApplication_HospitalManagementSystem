package com.nit.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.model.Doctor;
import com.nit.repository.IDoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private IDoctorRepository repository;

    public int addDoctor(Doctor doctor) {
        return repository.addDoctor(doctor);
    }

    public List<Doctor> getAllDoctors() {
        return repository.getDoctorDetails();
    }
    public Doctor getDoctorById(int id) {
    		return repository.getDoctorById(id);
    }
}
