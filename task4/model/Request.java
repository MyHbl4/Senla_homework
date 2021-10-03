package task4.model;

public class Request extends Identity {
  private long id = createRequestid();
  private int count = 1;
  private long bookId;
  private String title = null;

  public Request(long bookId) {
    this.bookId = bookId;
  }

  public Request(long id, int count, long bookId) {
    this.id = id;
    this.count = count;
    this.bookId = bookId;
  }

  public Request(long id, int count, long bookId, String title) {
    this.id = id;
    this.count = count;
    this.bookId = bookId;
    this.title = title;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getBookId() {
    return bookId;
  }

  public void setBookId(long bookId) {
    this.bookId = bookId;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Request - "
        + "ID: "
        + id
        + ", Count: "
        + count
        + ", Book ID: "
        + bookId
        + ", Title: '"
        + title
        + "'";
  }
}
