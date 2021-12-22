package com.moon.senla.dao;

import com.moon.senla.api.IRequestDao;
import com.moon.senla.entity.Request;
import com.moon.senla.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDao extends AbstractDao<Request> implements IRequestDao {
    @Override
    public void create(Request entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Request read(int id) {
        Request request = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            request = session.get(Request.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return request;
    }

    @Override
    public List<Request> readAll() {
        List<Request> requests = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Request> criteriaQuery = session.getCriteriaBuilder()
                .createQuery(Request.class);
            criteriaQuery.from(Request.class);
            requests = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return requests;
    }

    @Override
    public void update(Request entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Request entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
