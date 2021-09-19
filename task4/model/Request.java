package task4.model;

public class Request {
  private String title;
  private int count = 1;

  public Request(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  @Override
  public String toString() {
    return "Request{" + " title='" + title + '\'' + ", count=" + count + '}';
  }
}
