package task4.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFile {

  public String getPropertyValue(String propertyName) {
    String propertyValue = "";
    Properties properties = new Properties();

    try (InputStream inputStream = this.getClass().getResourceAsStream("app.properties")) {
      properties.load(inputStream);
      propertyValue = properties.getProperty(propertyName);
    } catch (IOException e) {
      System.out.println(e);
    }
    return propertyValue;
  }
}
