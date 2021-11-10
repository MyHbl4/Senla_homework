package com.moon.senla.factory;

import com.moon.senla.config.Config;
import com.moon.senla.config.JavaConfig;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {
  private static final ObjectFactory ourInstance = new ObjectFactory();
  private final Config config;
  private final List<ObjectConfigurator> configurators = new ArrayList<>();

  private ObjectFactory() {
    config = new JavaConfig("task4", new HashMap<>(Map.of()));
    for (Class<? extends ObjectConfigurator> aClass :
        config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
      try {
        configurators.add(aClass.getDeclaredConstructor().newInstance());
      } catch (InstantiationException
          | IllegalAccessException
          | InvocationTargetException
          | NoSuchMethodException e) {
        e.printStackTrace();
      }
    }
  }

  public static ObjectFactory getInstance() {
    return ourInstance;
  }

  public <T> T createObject(Class<T> type) {
    Class<? extends T> implClass = type;
    if (type.isInterface()) {
      implClass = config.getImplClass(type);
    }
    T t = null;
    try {
      // создаём объект
      t = implClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      e.printStackTrace();
    }
    T finalT = t;
    configurators.forEach(objectConfigurator -> objectConfigurator.configure(finalT));
    return t;
  }
}
