package com.moon.senla.factory;

import static java.util.stream.Collectors.toMap;

import com.moon.senla.annotations.InjectProperty;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

public class InjectPropertyAnnotationObjectConfiguratorImpl implements ObjectConfigurator {
  private final Map<String, String> propertiesMap;

  public InjectPropertyAnnotationObjectConfiguratorImpl() {
    String path = ClassLoader.getSystemClassLoader().getResource("app.properties").getPath();
    Stream<String> lines = null;
    try {
      lines = new BufferedReader(new FileReader(path)).lines();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    // создаём мапу из проперти файла(значения стринг разделяем через "="
    propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
  }

  @Override
  public void configure(Object t) {

    Class<?> implClass = t.getClass();
    for (Field declaredField : implClass.getDeclaredFields()) {
      InjectProperty annotation = declaredField.getAnnotation(InjectProperty.class);
      // задаём проперти файл
      if (annotation != null) {
        // значение аннотации пусто ? берём из мапы значение по имени : берём значение из аннотации
        String value =
            annotation.value().isEmpty()
                ? propertiesMap.get(declaredField.getName())
                : propertiesMap.get(annotation.value());
        declaredField.setAccessible(true);
        try {
          // устанавливаем у объекта у которого есть подходящее поле значение value
          declaredField.set(t, value);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
