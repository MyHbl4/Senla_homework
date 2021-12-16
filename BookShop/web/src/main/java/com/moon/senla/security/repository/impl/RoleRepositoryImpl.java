package com.moon.senla.security.repository.impl;

import com.moon.senla.security.model.Role;
import com.moon.senla.security.model.User;
import com.moon.senla.security.repository.RoleRepository;
import com.moon.senla.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public Role findByName(String name) {
            Role role = null;
            Session session = null;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                role = session.get(Role.class, name);
            } catch (HibernateException e) {
                e.printStackTrace();
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            return role;
    }
}
