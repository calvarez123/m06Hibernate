package com.project;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class Manager {

    private static SessionFactory factory; 
    
    public static void createSessionFactory() {

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
            configuration.getProperties()).build();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }

    public static void close () {
        factory.close();
    }
  

    public static <T> T getById(Class<? extends T> clazz, long id){
        Session session = factory.openSession();
        Transaction tx = null;
        T obj = null;
        try {
            tx = session.beginTransaction();
            obj = clazz.cast(session.get(clazz, id)); 
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return obj;
    }


    

  
    public static <T> void delete(Class<? extends T> clazz, Serializable id){
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            T obj = clazz.cast(session.get(clazz, id)); 
            session.delete(obj); 
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz) {
        return listCollection(clazz, "");
    }

    public static <T> Collection<?> listCollection(Class<? extends T> clazz, String where){
        Session session = factory.openSession();
        Transaction tx = null;
        Collection<?> result = null;
        try {
            tx = session.beginTransaction();
            if (where.length() == 0) {
                result = session.createQuery("FROM " + clazz.getName()).list(); 
            } else {
                result = session.createQuery("FROM " + clazz.getName() + " WHERE " + where).list();
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return result;
    }

    public static <T> String collectionToString(Class<? extends T> clazz, Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return "No records found.";
        }
    
        StringBuilder txt = new StringBuilder();
        for (Object obj : collection) {
            T cObj = clazz.cast(obj);
            txt.append("\n").append(cObj.toString());
        }
    
        return txt.toString().substring(1); // Remove the leading newline character
    }
    

    public static void queryUpdate (String queryString) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(queryString);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
    }

    public static List<Object[]> queryTable (String queryString) {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Object[]> result = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery(queryString);
            @SuppressWarnings("unchecked")
            List<Object[]> rows = query.list();
            result = rows;
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return result;
    }

    public static String tableToString(List<Object[]> rows) {
        StringBuilder txt = new StringBuilder();
    
        for (Object[] row : rows) {
            for (Object cell : row) {
                txt.append(cell.toString()).append(", ");
            }
    
            int length = txt.length();
            if (length >= 2 && txt.substring(length - 2).equals(", ")) {
                txt.delete(length - 2, length);
            }
    
            txt.append("\n");
        }
    
        int finalLength = txt.length();
        if (finalLength >= 2) {
            txt.delete(finalLength - 2, finalLength);
        }
    
        return txt.toString();
    }
    
    //---------------------------------------------------------------------------------------------------------
    // Inside Manager class
public static Ciutat addCiutat(String nom, String pais, int codiPostal) {
    Session session = factory.openSession();
    Transaction tx = null;
    Ciutat result = null;
    try {
        tx = session.beginTransaction();
        result = new Ciutat(nom, pais, codiPostal);
        session.save(result);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return result;
}


public static Ciutada addCiutada(long ciutatId, String nom, String cognom, int edat) {
    Session session = factory.openSession();
    Transaction tx = null;
    Ciutada result = null;
    try {
        tx = session.beginTransaction();
        result = new Ciutada(ciutatId, nom, cognom, edat);
        session.save(result);
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return result;
}


    public static void updateCiutat(long ciutatId, String nom, String pais, int codiPostal) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Ciutat obj = (Ciutat) session.get(Ciutat.class, ciutatId);
            obj.setNom(nom);
            obj.setPais(pais);
            obj.setCodiPostal(codiPostal);
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateCiutada(long ciutadaId, long ciutatId, String nom, String cognom, int edat) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Ciutada obj = (Ciutada) session.get(Ciutada.class, ciutadaId);
            obj.setCiutatId(ciutatId);
            obj.setNom(nom);
            obj.setCognom(cognom);
            obj.setEdat(edat);
            session.update(obj);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteCiutat(long ciutatId) {
        delete(Ciutat.class, ciutatId);
    }

    public static void deleteCiutada(long ciutadaId) {
        delete(Ciutada.class, ciutadaId);
    }

    public static Ciutat getCiutatById(long ciutatId) {
        return getById(Ciutat.class, ciutatId);
    }

    public static Ciutada getCiutadaById(long ciutadaId) {
        return getById(Ciutada.class, ciutadaId);
    }
}
