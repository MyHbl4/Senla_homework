package com.moon.senla.repository.impl;

import com.moon.senla.entity.Role;
import com.moon.senla.repository.RoleRepository;
import com.moon.senla.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Component
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Role findByName(String name) {
        Role role = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            role = session.get(Role.class, name);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return role;
    }
}
