package task4.util;

import task4.model.Book;

public class BookArrayList {
  private final int INIT_SIZE = 16;
  private final int CUT_RATE = 4;
  private Object[] array = new Object[INIT_SIZE];
  private int pointer = 0;

  /*
  Добавляет новый элемент в список. При достижении размера внутреннего
  массива происходит его увеличение в два раза.
  */
  public void add(Book book) {
    if (pointer == array.length - 1)
      resize(array.length * 2); // увеличу в 2 раза, если достигли границ
    array[pointer++] = book;
  }

  /*
  Возвращает элемент списка по индексу.
  */
  public Book get(int index) {
    return (Book) array[index];
  }

  /*
  Удаляет элемент списка по индексу. Все элементы справа от удаляемого
  перемещаются на шаг налево. Если после удаления элемента количество
  элементов стало в CUT_RATE раз меньше чем размер внутреннего массива,
  то внутренний массив уменьшается в два раза, для экономии занимаемого
  места.
  */
  public void remove(int index) {
    for (int i = index; i < pointer; i++) array[i] = array[i + 1];
    array[pointer] = null;
    pointer--;
    if (array.length > INIT_SIZE && pointer < array.length / CUT_RATE)
      resize(array.length / 2); // если элементов в CUT_RATE раз меньше чем
    // длина массива, то уменьшу в два раза
  }
  /*Возвращает количество элементов в списке*/
  public int size() {
    return pointer;
  }

  /*Вспомогательный метод для масштабирования.*/
  private void resize(int newLength) {
    Object[] newArray = new Object[newLength];
    System.arraycopy(array, 0, newArray, 0, pointer);
    array = newArray;
  }

  public int indexOf(Object o) {
    int result = -1;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == o) {
        result = i;
        break;
      }
    }
    return result;
  }

  public void print() {
    for (int i = 0; i < array.length; i++) {
      if (array[i] != null) {
        System.out.println(array[i]);
      }
    }
  }
}
