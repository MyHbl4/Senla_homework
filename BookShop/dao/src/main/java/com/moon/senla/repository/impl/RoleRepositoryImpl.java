package com.moon.senla.repository.impl;

import com.moon.senla.entity.Role;
import com.moon.senla.entity.User;
import com.moon.senla.repository.RoleRepository;
import com.moon.senla.util.HibernateUtil;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Role findByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaQuery<Role> criteriaQuery = session.getCriteriaBuilder().createQuery(Role.class);
        Root<Role> userRequest = criteriaQuery.from(Role.class);

        Expression<String> exp = userRequest.get("name");
        Predicate predicate = exp.in(name);

        criteriaQuery.where(predicate);
        try {
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            return new Role();
        }
    }
}
