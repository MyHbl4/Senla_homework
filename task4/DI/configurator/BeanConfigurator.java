package task4.DI.configurator;

public interface BeanConfigurator {

  <T> Class<? extends T> getImplementationClass(Class<T> interfaceClass);

}
