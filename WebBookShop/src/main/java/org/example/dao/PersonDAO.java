package org.example.dao;

import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import org.example.config.HibernateUtil;
import org.example.models.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class PersonDAO {
//    private static int PEOPLE_COUNT;
//    private final List<Person> people;
//
//    {
//        people = new ArrayList<>();
//        people.add(new Person(++PEOPLE_COUNT, "Mark", 3, "mark_the_best@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Irina", 26, "ira_pirojok@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Lydia", 1, "star_my_heart@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Aleksandr", 33, "aleksandr.petushenko1@gmail.com"));
//    }

    public List<Person> index() {
        List<Person> people2 = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            CriteriaQuery<Person> criteriaQuery = session.getCriteriaBuilder()
                .createQuery(Person.class);
            criteriaQuery.from(Person.class);
            people2 = session.createQuery(criteriaQuery).getResultList();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return people2;
    }

    public Person show(int id) {
        Person person = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            person = session.get(Person.class, id);
        } catch (
            HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return person;
    }

    public void save(Person person) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void update(Person entity) {
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

    public void delete(Person entity) {
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
//    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(
//            null);//проходимся по всем персонам и сравниваем их id с переданным и возвращаем персона если такого нет возвращаем нул
//    }
//    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
//    }
//
//    public void update(int id, Person updatePerson) {
//        Person personToBeUpdated = show(id); //находим по id через метод show
//        personToBeUpdated.setName(
//            updatePerson.getName()); //устанавливаем нужное имя такое как у переданного
//        personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
//        //изменяем все поля которые нам нужны
//    }
//
//    public void delete(int id) {
//        people.removeIf(person -> person.getId()
//            == id);//проходимся по каждому персону, сравниваем его id c переданным id, после нахождения нужного удаляет его из списка
//    }
//}
