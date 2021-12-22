package com.moon.senla;

import com.moon.senla.entity.Request;
import java.util.List;

public interface RequestService {

    void addRequest(long bookId);

    List<Request> sortRequestByCount();

    List<Request> sortRequestByTitle();

    void create(Request entity);

    Request read(int id);

    List<Request> readAll();

    void update(Request entity);

    void delete(Request entity);
}
