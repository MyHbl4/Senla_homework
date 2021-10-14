package task4.DI.factory;

import static java.util.stream.Collectors.toMap;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import task4.DI.annotations.InjectProperty;
import task4.DI.config.Config;
import task4.DI.config.JavaConfig;
import task4.UI.MenuController;

public class ObjectFactory {
  private static ObjectFactory ourInstance = new ObjectFactory();
  private Config config;

  private ObjectFactory() {
    config =
        new JavaConfig("task4", new HashMap<>(Map.of(MenuController.class, MenuController.class)));
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
      //создаём объект
      t = implClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      e.printStackTrace();
    }
    for (Field declaredField : implClass.getDeclaredFields()) {
      InjectProperty annotation = declaredField.getAnnotation(InjectProperty.class);
      //задаём проперти файл
      String path = ClassLoader.getSystemClassLoader().getResource("app.properties").getPath();

      Stream<String> lines = null;
      try {
        //читаем проперти
        lines = new BufferedReader(new FileReader(path)).lines();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
      //создаём мапу из проперти файла(значения стринг разделяем через "="
      Map<String, String> propertiesMap =
          lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));

      if (annotation != null) {
        //значение аннотации пусто ? берём из мапы значение по имени : берём значение из аннотации
        String value =
            annotation.value().isEmpty()
                ? propertiesMap.get(declaredField.getName())
                : propertiesMap.get(annotation.value());
        declaredField.setAccessible(true);
        try {
          //устанавливаем у объекта у которого есть подходящее поле значение value
          declaredField.set(t,value);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return t;
  }
}
