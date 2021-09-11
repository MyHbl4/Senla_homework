package task4.model;

public class Buyer extends Person{
    private String address;

    public Buyer(long id, String name, int age, String address) {
        super(id, name, age);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Buyer{" + super.toString() +
                ", address='" + address + '\'' +
                "} ";
    }
}
