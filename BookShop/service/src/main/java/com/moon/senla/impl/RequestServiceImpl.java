package com.moon.senla.impl;

import java.util.Comparator;
import java.util.List;

import com.moon.senla.RequestService;
import com.moon.senla.entity.Request;
import com.moon.senla.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestServiceImpl implements RequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);
    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
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
}
