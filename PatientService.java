package com.nit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.model.Patient;
import com.nit.repository.IPatientRepository;

@Service
public class PatientService {

    @Autowired
    private IPatientRepository repository;

    // Add Patient
    public int addPatient(Patient patient) {

        // Business Logic Example
        if (patient.getAge() <= 0) {
            throw new RuntimeException("Invalid Age");
        }

        return repository.addPatient(patient);
    }

    // Get All Patients
    public List<Patient> getAllPatients() {
        return repository.getPatientDetails();
    }
    
    //Get Patient By Id
    public Patient getPatientById(int patientId) {
    		return repository.getPatientById(patientId);
    }

    // Delete Patient
    public int deletePatient(int id) {
        return repository.delete(id);
    }
}
