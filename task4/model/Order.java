package task4.model;

import java.util.Arrays;

public class Order {
    private long id;
    private long buyerId;
    private long[] booksId;
    private OrderStatus orderStatus = OrderStatus.NEW;

    public Order(long id, long buyerId, long[] booksId) {
        this.id = id;
        this.buyerId = buyerId;
        this.booksId = booksId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long[] getBooksId() {
        return booksId;
    }

    public void setBooksId(long[] booksId) {
        this.booksId = booksId;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatusNew() {
        this.orderStatus = OrderStatus.NEW;
    }

    public void setOrderStatusCompleate() {
        this.orderStatus = OrderStatus.COMPLEATE;
    }

    public void setOrderStatusCanceled() {
        this.orderStatus = OrderStatus.CANCELED;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", booksId=" + Arrays.toString(booksId) +
                ", orderStatus=" + orderStatus +
                '}';
    }
}

