package task4.DI.factory;

import java.lang.reflect.Field;
import task4.DI.annotations.InjectByType;

public class InjectByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

  @Override
  public void configure(Object t) {
    for (Field field : t.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(InjectByType.class)) {
        field.setAccessible(true);
        Object object = ObjectFactory.getInstance().createObject(field.getType());
        try {
          field.set(t, object);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
