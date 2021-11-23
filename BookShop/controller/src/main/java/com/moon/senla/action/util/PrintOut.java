package com.moon.senla.action.util;

import java.util.List;

public class PrintOut {

    public <T> void printList(List<T> list) {
        for (T l : list) {
            System.out.println(l);
        }
    }

    public <T> void printEntity(T entity) {
        System.out.println(entity);
    }
}
