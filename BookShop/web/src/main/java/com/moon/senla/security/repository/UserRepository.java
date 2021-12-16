package com.moon.senla.security.repository;

import com.moon.senla.security.model.User;
import java.util.List;
import org.springframework.stereotype.Component;

public interface UserRepository {

    User findByUserName(String name);

    User save(User user);

    List<User> findAll();

    User findById(long id);

    void deleteById(long id);
}
