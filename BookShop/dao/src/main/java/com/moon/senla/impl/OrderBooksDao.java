package com.moon.senla.impl;

import java.util.List;

import com.moon.senla.HibernateUtil;
import com.moon.senla.IOrderBooksDao;
import com.moon.senla.entity.OrderBooks;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class OrderBooksDao extends AbstractDao<OrderBooks> implements IOrderBooksDao {
  @Override
  public void create(OrderBooks entity) {
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
  public OrderBooks read(int id) {
    OrderBooks orderBooks = null;
    Session session = null;
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      orderBooks = session.get(OrderBooks.class, id);
    } catch (HibernateException e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return orderBooks;
  }

  @Override
  public List<OrderBooks> readAll() {
      List<OrderBooks> orderBooks = null;
      Session session = null;
      try {
          session = HibernateUtil.getSessionFactory().openSession();
          CriteriaQuery<OrderBooks> criteriaQuery = session.getCriteriaBuilder().createQuery(OrderBooks.class);
          criteriaQuery.from(OrderBooks.class);
          orderBooks = session.createQuery(criteriaQuery).getResultList();
      } catch (HibernateException e) {
          e.printStackTrace();
      } finally {
          if (session != null) {
              session.close();
          }
      }
    return orderBooks;
  }

  @Override
  public void update(OrderBooks entity) {
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
  public void delete(OrderBooks entity) {
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
