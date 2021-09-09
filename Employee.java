public class Employee extends Person {
    private String position;
    private int salary;
    private int bonus;

    public Employee(String name, String sex, String position, int salary, int bonus) {
        super(name, sex);
        this.position = position;
        this.salary = salary;
        this.bonus = bonus;
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

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Employee{" + "name:" + super.getName() + ", sex:" + super.getSex() +
                ", position:" + position + ", salary:" + salary + "} ";
    }
}
