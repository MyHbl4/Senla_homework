package com.moon.senla.impl;

import com.moon.senla.RequestService;
import com.moon.senla.dao.RequestDao;
import com.moon.senla.entity.Request;
import com.moon.senla.repository.RequestRepository;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);
    private final RequestRepository requestRepository;
    private final RequestDao requestDao;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository,
        RequestDao requestDao) {
        this.requestRepository = requestRepository;
        this.requestDao = requestDao;
    }

    @Override
    public void addRequest(long bookId) {
        try {
            requestRepository.addRequest(bookId);
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
    }

    @Override
    public List<Request> sortRequestByCount() {
        List<Request> sortRequests = null;
        try {
            sortRequests = requestRepository.getAll();
            sortRequests.sort(Comparator.comparingInt(Request::getCount));
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return sortRequests;
    }

    @Override
    public List<Request> sortRequestByTitle() {
        List<Request> sortRequests = null;
        try {
            sortRequests = requestRepository.getAll();
            sortRequests.sort(Comparator.comparing(Request::getTitle));
            LOGGER.info(
                "Method completed - " + Thread.currentThread().getStackTrace()[1].getMethodName());
        } catch (Exception e) {
            LOGGER.warn(
                "Failed to execute the method - "
                    + Thread.currentThread().getStackTrace()[1].getMethodName(),
                e);
        }
        return sortRequests;
    }

    @Override
    public void create(Request entity) {
        requestDao.create(entity);
    }

    @Override
    public Request read(int id) {
        return requestDao.read(id);
    }

    @Override
    public List<Request> readAll() {
        return requestDao.readAll();
    }

    @Override
    public void update(Request entity) {
        requestDao.update(entity);
    }

    @Override
    public void delete(Request entity) {
        requestDao.delete(entity);
    }
}
