public class Employee extends Person {
    private String position;
    private int salary;

    public Employee(String name, String sex, String position, int salary) {
        super(name, sex);
        this.position = position;
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" + "name:" + super.getName() + ", sex:" + super.getSex() +
                ", position:" + position + ", salary:" + salary + "} ";
    }
}
