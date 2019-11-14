package com.haulmont.web.controller;

import com.haulmont.web.model.Recipe;
import com.haulmont.web.model.Doctor;
import com.haulmont.web.model.Patient;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Recipe.class);
                configuration.addAnnotatedClass(Doctor.class);
                configuration.addAnnotatedClass(Patient.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
        return sessionFactory;
    }
}