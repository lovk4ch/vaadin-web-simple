package com.haulmont.web.controller.dao;

import com.haulmont.web.controller.HibernateSessionFactoryUtil;
import com.haulmont.web.model.entity.Doctor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import java.util.List;

public class DoctorDAO {

    public Doctor findById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Doctor doctorEntity = session.get(Doctor.class, id);
        session.close();
        return doctorEntity;
    }

    public void save(Doctor doctorEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(doctorEntity);
        tx1.commit();
        session.close();
    }

    public void update(Doctor doctorEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(doctorEntity);
        tx1.commit();
        session.close();
    }

    public void delete(Doctor doctorEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(doctorEntity);
        tx1.commit();
        session.close();
    }

    public List<Object[]> getStatistics() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List list = session.createSQLQuery(
                "SELECT D.first_name as first_name, D.last_name as last_name, (SELECT COUNT (R.doctor)"
                        + " from recipe R where R.doctor = D.ID)"
                        + " as recipe from doctor D")
                .addScalar("first_name", StringType.INSTANCE)
                .addScalar("last_name", StringType.INSTANCE)
                .addScalar("recipe", IntegerType.INSTANCE)
                .list();
        session.close();
        return list;
    }

    public List<Doctor> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Doctor> users = (List<Doctor>) session.createQuery("From Doctor ").list();
        session.close();
        return users;
    }
}