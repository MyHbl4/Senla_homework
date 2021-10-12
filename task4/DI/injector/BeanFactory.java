package task4.DI.injector;

import java.lang.reflect.InvocationTargetException;
import task4.DI.config.Configuration;
import task4.DI.config.JavaConfiguration;
import task4.DI.configurator.BeanConfigurator;
import task4.DI.configurator.JavaBeanConfigurator;

public class BeanFactory {

  private static final BeanFactory BEAN_FACTORY = new BeanFactory();
  private final BeanConfigurator beanConfigurator;
  private final Configuration configuration;

  public BeanFactory() {
    this.configuration = new JavaConfiguration();
    this.beanConfigurator = new JavaBeanConfigurator(configuration.getPackageToScan());
  }

  public static BeanFactory getInstance(){
    return BEAN_FACTORY;
  }

  public <T> T getBean(Class<T> clazz){
    Class<? extends T> implementationClass = clazz;
    if(implementationClass.isInterface()){
      implementationClass = beanConfigurator.getImplementationClass(implementationClass);
    }
    T t = null;
    try {
      t = implementationClass.getDeclaredConstructor().newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
    }
    return t;
  }
}
