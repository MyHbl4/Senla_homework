package task4.model;

public class Request {
  private String title;
  private static int id=1;

  public Request(String title) {
    this.title = title;
    id++;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public static int getId() {
    return id;
  }

  public static void setId(int id) {
    Request.id = id;
  }

  @Override
  public String toString() {
    return "Request{" +
        "title='" + title + '\'' +
        '}';
  }
}
