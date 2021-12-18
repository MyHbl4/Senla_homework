package com.moon.senla.repository;

import com.moon.senla.model.Role;

public interface RoleRepository {
    Role findByName(String name);
}
