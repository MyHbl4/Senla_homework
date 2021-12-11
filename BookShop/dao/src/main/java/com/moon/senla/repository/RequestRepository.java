package com.moon.senla.repository;

import com.moon.senla.entity.Request;
import java.util.List;

public interface RequestRepository {

    List<Request> getAll();

    void addRequest(long bookId);

    Request findRequestById(int id);
}
