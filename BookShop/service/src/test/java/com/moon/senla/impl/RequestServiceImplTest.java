package com.moon.senla.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.moon.senla.dao.BookDao;
import com.moon.senla.dao.RequestDao;
import com.moon.senla.entity.Book;
import com.moon.senla.entity.Request;
import com.moon.senla.repository.RequestRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private RequestDao requestDao;

    @Mock
    private BookDao bookDao;

    @InjectMocks
    private RequestServiceImpl requestService;

    private List<Request> requests = Arrays.asList(
        new Request(new Book("Test_title", "Test_author", 200, 2021)),
        new Request(new Book("Test_title1", "Test_author1", 100, 2011)),
        new Request(new Book("Test_title2", "Test_author2", 200, 2012)),
        new Request(new Book("Test_title3", "Test_author3", 300, 2013)));

    private List<Book> books = Arrays.asList(
        new Book("Test_title", "Test_author", 200, 2021),
        new Book("Test_title1", "Test_author1", 100, 2011),
        new Book("Test_title2", "Test_author2", 200, 2012),
        new Book("Test_title3", "Test_author3", 300, 2013));

    private Request setRequest() {
        Request request = requests.get(0);
        request.setId(1L);
        return request;
    }

    private Book setBook() {
        Book book = books.get(0);
        book.setId(1L);
        return book;
    }

    @Test
    void addRequest() {
        Book book = setBook();
        List<Book> bookList = books;
        bookList.get(0).setId(1L);
        bookList.get(1).setId(2L);
        bookList.get(2).setId(3L);
        bookList.get(3).setId(4L);
        Request request = new Request(book);
        request.setId(3L);
        List<Request> requestList = Arrays.asList(
            new Request(bookList.get(1)),
            new Request(bookList.get(2)));
        requestList.get(0).setId(1L);
        requestList.get(1).setId(2L);
        requestService.addRequest(request.getBook().getId());
        verify(requestRepository, times(1)).addRequest(request.getBook().getId());
    }


    @Test
    void create() {
        Request request = setRequest();
        requestService.create(request);
        verify(requestDao, times(1)).create(request);
    }

    @Test
    void read() {
        Request findRequest = setRequest();
        when(requestService.read((int) findRequest.getId())).thenReturn(findRequest);
        Request request = requestService.read((int) findRequest.getId());
        assertThat(request.getId(), equalTo(1L));
        assertThat(request.getTitle(), equalTo("Test_title"));
        assertThat(request.getCount(), equalTo(1));
        assertThat(request.getBook(), equalTo(findRequest.getBook()));

    }

    @Test
    void readAll() {
        when(requestService.readAll()).thenReturn(requests);
        List<Request> requestList = requestService.readAll();
        assertThat(requestList.size(), equalTo(4));
        assertThat(requestList.get(0), equalTo(requests.get(0)));
        assertThat(requestList.get(1), equalTo(requests.get(1)));
        assertThat(requestList.get(2), equalTo(requests.get(2)));
        assertThat(requestList.get(3), equalTo(requests.get(3)));
    }

    @Test
    void update() {
        Request request = setRequest();
        when(requestService.read((int) request.getId())).thenReturn(request);
        Request updateRequest = requestService.read((int) request.getId());
        requestService.update(updateRequest);
        verify(requestDao, times(1)).update(updateRequest);
    }

    @Test
    void delete() {
        Request request = setRequest();
        when(requestService.read((int) request.getId())).thenReturn(request);
        Request deleteRequest = requestService.read((int) request.getId());
        requestService.delete(deleteRequest);
        verify(requestDao, times(1)).delete(deleteRequest);
    }
}