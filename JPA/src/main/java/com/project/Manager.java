package com.project;

import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Manager {
    private static SessionFactory sessionFactory;

    public static void createSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
    }

    public static Ciutat addCiutat(String nom, String pais, int codiPostal) {
        Ciutat ciutat = new Ciutat();
        ciutat.setNom(nom);
        ciutat.setPais(pais);
        ciutat.setCodiPostal(codiPostal);
        saveOrUpdate(ciutat);
        return ciutat;
    }

    public static Ciutada addCiutada(long ciutatId, String nom, String cognom, int edat) {
        Ciutada ciutada = new Ciutada();
        ciutada.setCiutatId(ciutatId);
        ciutada.setNom(nom);
        ciutada.setCognom(cognom);
        ciutada.setEdat(edat);
        saveOrUpdate(ciutada);
        return ciutada;
    }

    public static void delete(Class<?> entityClass, long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Object entity = session.get(entityClass, id);
            if (entity != null) {
                session.delete(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static Collection<?> listCollection(Class<?> entityClass, String condition) {
        Session session = sessionFactory.openSession();
        try {
            String queryString = "FROM " + entityClass.getSimpleName();
            if (!condition.isEmpty()) {
                queryString += " WHERE " + condition;
            }
            return session.createQuery(queryString).list();
        } finally {
            session.close();
        }
    }

    private static void saveOrUpdate(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void close() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
