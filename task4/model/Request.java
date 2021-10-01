package task4.model;

public class Request extends Identity {
  private long id = createRequestid();
  private String title;
  private int count = 1;

  public Request(long id, int count, String title) {
    this.id = id;
    this.title = title;
    this.count = count;
  }

  public Request(String title) {
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
    return "Request[ " + " id: " + id + ", count: " + count + ",  title: '" + title + '\'' + " ] ";
  }
}
