package com.moon.senla.security.repository;

import com.moon.senla.security.model.Role;
import org.springframework.stereotype.Component;


public interface RoleRepository {
    Role findByName(String name);
}
