package task4.DI.config;

import java.util.Map;
import java.util.Set;
import org.reflections.Reflections;

public class JavaConfig implements Config {
  private final Map<Class, Class> interfaceToImplClass;
  private final Reflections scanner;

  public JavaConfig(String packageToScan, Map<Class, Class> interfaceToImplClass) {
    this.interfaceToImplClass = interfaceToImplClass;
    this.scanner = new Reflections(packageToScan);
  }

  @Override
  public <T> Class<? extends T> getImplClass(Class<T> tClass) {
    // принимает ключ и если ключ существует возвращает значение для этого ключа
    return interfaceToImplClass.computeIfAbsent(
        tClass,
        aClass -> {
          // если ключ не существует, запускается лямбда, а лямбда возвращает значение
          // это значение сетится в мапу на будущее
          Set<Class<? extends T>> classes = scanner.getSubTypesOf(tClass);
          if (classes.size() != 1) {
            throw new RuntimeException(
                tClass + " has 0 or more than one impl please update your config");
          }
          return classes.iterator().next();
        });
  }

  @Override
  public Reflections getScanner() {
    return scanner;
  }
}
