package task4.jdbc;

import java.util.List;

public abstract class AbstractDAO<K extends Number, T> {
  public abstract boolean create(T entity);

  public abstract List<T> readAll();

  public abstract T read(K id);

  public abstract boolean update(K id);

  public abstract boolean delete(K id);
}
