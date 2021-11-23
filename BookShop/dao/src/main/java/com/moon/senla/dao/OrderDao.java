package com.moon.senla.dao;

import java.util.List;

import com.moon.senla.HibernateUtil;
import com.moon.senla.api.IOrderDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Order;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class OrderDao extends AbstractDao<Order> implements IOrderDao {
  @Override
  public void create(Order entity) {
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
  public void createOrderBooks(Order entity) {
    List<Book> books = entity.getBooks();
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
  public Order read(int id) {
    Order order = null;
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      order = session.get(Order.class, id);
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return order;
  }

  @Override
  public List<Book> readBooks(Integer id) {

    return read(id).getBooks();
  }

  @Override
  public List<Order> readAll() {
    List<Order> orders = null;
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      CriteriaQuery<Order> criteriaQuery = session.getCriteriaBuilder().createQuery(Order.class);
      criteriaQuery.from(Order.class);
      orders = session.createQuery(criteriaQuery).getResultList();
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return orders;
  }

  @Override
  public void update(Order entity) {
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
  public void delete(Order entity) {
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
