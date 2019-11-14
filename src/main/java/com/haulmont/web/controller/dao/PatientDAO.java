package com.haulmont.web.controller.dao;

import com.haulmont.web.controller.HibernateSessionFactoryUtil;
import com.haulmont.web.model.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PatientDAO {

    public Patient findById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Patient patientEntity = session.get(Patient.class, id);
        session.close();
        return patientEntity;
    }

    public void save(Patient patientEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(patientEntity);
        tx1.commit();
        session.close();
    }

    public void update(Patient patientEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(patientEntity);
        tx1.commit();
        session.close();
    }

    public void delete(Patient patientEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(patientEntity);
        tx1.commit();
        session.close();
    }

    public List<Patient> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Patient> users = (List<Patient>) session.createQuery("From Patient ").list();
        session.close();
        return users;
    }
}