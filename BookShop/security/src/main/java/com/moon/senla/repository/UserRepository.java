package com.moon.senla.repository;

import com.moon.senla.model.User;
import java.util.List;

public interface UserRepository {

    User findByUsername(String username);

    User save(User user);

    List<User> findAll();

    User findById(long id);

    void deleteById(long id);
}
