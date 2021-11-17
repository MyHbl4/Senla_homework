package com.moon.senla;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {
  public static void main(String[] args) {
    Transaction transaction = null;
    try (
        Session session = HibernateUtil.getSessionFactory().openSession()
    ) {
      Author author = new Author(4L, "Vasya111", "Pupkin");
      transaction = session.beginTransaction();
      session.save(author);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }

    try (
        Session session = HibernateUtil.getSessionFactory().openSession()
    ) {
      Author author = new Author(7L, "Vasya222", "Pupkin2");
      transaction = session.beginTransaction();
      session.save(author);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
}