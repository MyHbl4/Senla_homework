package com.moon.senla.impl.tester;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Rap implements Music {
    private List<String> list = new ArrayList<>();

    {
        list.add("rap1");
        list.add("rap2");
        list.add("rap3");
    }

    @Override
    public List<String> getMusic() {
        return list;
    }
}
