package task4.DI.config;

public class JavaConfiguration implements Configuration {

  @Override
  public String getPackageToScan() {
    return "task4";
  }
}
