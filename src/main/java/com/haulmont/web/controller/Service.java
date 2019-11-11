package com.haulmont.web.controller;

import com.haulmont.web.controller.dao.DoctorDAO;
import com.haulmont.web.controller.dao.PatientDAO;
import com.haulmont.web.model.entity.Doctor;
import com.haulmont.web.model.entity.Patient;

import java.util.List;

public class Service {
    private DoctorDAO doctorDAO = new DoctorDAO();
    private PatientDAO patientDAO = new PatientDAO();

    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    /* ----- Doctor service block ----- */

    public Doctor findDoctor(long id) {
        return doctorDAO.findById(id);
    }

    public void deleteDoctor(Doctor doctor) {
        doctorDAO.delete(doctor);
    }

    public void saveDoctor(Doctor doctor) {
        if (doctor.getId() == 0)
            doctorDAO.save(doctor);
        else
            doctorDAO.update(doctor);
    }

    public List<Object[]> getStatistics() {
        return doctorDAO.getStatistics();
    }

    public List<Doctor> findAllDoctors() {
        return doctorDAO.findAll();
    }



    /* ----- Patient service block ----- */

    public Patient findPatient(long id) {
        return patientDAO.findById(id);
    }

    public void deleteDoctor(Patient patient) {
        patientDAO.delete(patient);
    }

    public void savePatient(Patient patient) {
        if (patient.getId() == 0)
            patientDAO.save(patient);
        else
            patientDAO.update(patient);
    }

    public List<Patient> findAllPatients() {
        return patientDAO.findAll();
    }
}