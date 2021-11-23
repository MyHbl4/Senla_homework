package com.moon.senla.dao;

import java.util.List;

import com.moon.senla.api.IBookDao;
import com.moon.senla.entity.Book;
import com.moon.senla.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class BookDao extends AbstractDao<Book> implements IBookDao {
    @Override
    public void create(Book entity) {
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
    public Book read(int id) {
        Book book = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            book = session.get(Book.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return book;
    }

    @Override
    public List<Book> readAll() {
        List<Book> books = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Book> criteriaQuery = session.getCriteriaBuilder().createQuery(Book.class);
            criteriaQuery.from(Book.class);
            books = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return books;
    }

    @Override
    public void update(Book entity) {
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
    public void delete(Book entity) {
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
