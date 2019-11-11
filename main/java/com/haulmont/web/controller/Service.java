package com.haulmont.web.controller;

import com.haulmont.web.controller.dao.DoctorDAO;
import com.haulmont.web.model.entity.Doctor;

import java.util.List;

public class Service {
    DoctorDAO doctorDAO = new DoctorDAO();

    private static Service instance;

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

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
}