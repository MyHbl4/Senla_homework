package task4.datasource;

import task4.model.*;
import task4.other.BookArrayList;
import task4.other.MyArrayList;

public class BookDataSourceImpl implements BookDataSource{
    private BookArrayList<Book> books = new BookArrayList<>();
    private MyArrayList<Order> orders = new MyArrayList<>();
    private MyArrayList<Buyer> buyers = new MyArrayList<>();

    public BookDataSourceImpl(){ initData();}

    public void initData(){

        books.add(new Book(1, "Война и мир", "Лев Толстой", 150, Genre.NOVEL, Availability.IN_STOCK));
        books.add(new Book(2, "Ромео и Джульетта", "Уильям Шекспир", 50, Genre.NOVEL, Availability.IN_STOCK));
        books.add(new Book(3, "Десять негритят", "Агата Кристи", 70, Genre.DETECTIVE, Availability.IN_STOCK));
        books.add(new Book(4, "Java руководство для начинающих", "Герберт Шилдт", 150, Genre.EDUKATIONAL_BOOK, Availability.IN_STOCK));
        books.add(new Book(5, "Приключения Тома Сойера", "Марк Твен", 40, Genre.NOVEL, Availability.OUT_OF_STOCK));
        books.add(new Book(6, "Хоббит, или Туда и Обратно", "Джон Толкин", 120, Genre.SCIENCE_FICTION, Availability.IN_STOCK));
        books.add(new Book(7, "Гарри Поттер и филосовский камень", "Джоан Роулинг", 100, Genre.NOVEL, Availability.OUT_OF_STOCK));
        books.add(new Book(8, "Маленький принц", "Антуана де Сент-Экзюпери", 50, Genre.CHILDREN_BOOK, Availability.IN_STOCK));
        books.add(new Book(9, "Властелин колец", "Джон Толкин", 70, Genre.SCIENCE_FICTION, Availability.IN_STOCK));
        books.add(new Book(10, "Повесть о двух городах", "Чарльз Дикенс", 80, Genre.NOVEL, Availability.IN_STOCK));

        buyers.add(new Buyer(1,"Вася Сюткин", 28, "Гомель, ул. Войкова 256"));
        buyers.add(new Buyer(2,"Антон Шкуратов", 32, "Гомель, пр-т Речицкий 67"));
        buyers.add(new Buyer(3,"Вася Сюткин", 32, "Гомель, ул. Международная 35"));

        orders.add(new Order(1,2, new long[]{1,2,5} ));
        orders.add(new Order(2,3, new long[]{3,4,7} ));
        orders.add(new Order(3,1, new long[]{2,6,8} ));
    }

    @Override
    public BookArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public MyArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public MyArrayList<Buyer> getBuyers() {
        return buyers;
    }
}
