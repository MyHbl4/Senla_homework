package com.moon.senla.repository;

import com.moon.senla.entity.Role;

public interface RoleRepository {
    Role findByName(String name);
}
