package task4.DI.factory;

import java.lang.reflect.InvocationTargetException;
import task4.DI.config.Config;
import task4.DI.config.JavaConfig;

public class ObjectFactory {
  private static ObjectFactory ourInstance = new ObjectFactory();
  private Config config = new JavaConfig("task4");

  private ObjectFactory() {}

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
      t = implClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
    }
    return t;
  }
}
