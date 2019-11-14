package com.haulmont.web.controller.dao;

import com.haulmont.web.controller.HibernateSessionFactoryUtil;
import com.haulmont.web.model.Recipe;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RecipeDAO {

    public Recipe findById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Recipe recipeEntity = session.get(Recipe.class, id);
        session.close();
        return recipeEntity;
    }

    public void save(Recipe recipeEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(recipeEntity);
        tx1.commit();
        session.close();
    }

    public void update(Recipe recipeEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(recipeEntity);
        tx1.commit();
        session.close();
    }

    public void delete(Recipe recipeEntity) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(recipeEntity);
        tx1.commit();
        session.close();
    }

    public List<Recipe> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Recipe> recipes = (List<Recipe>) session.createQuery("From Recipe ").list();
        session.close();
        return recipes;
    }
}