package task3;

public class Company {
    private String name;
    private String industry;
    private Employee[] employees = new Employee[5];
    private int count = 0;

    public Company(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    private void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public int getCount() {
        return count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public int salaryAmount() {
        int sum = 0;
        for (Employee e : employees) {
            sum += e.getSalary() + e.getBonus();
        }
        return sum;
    }

    public void addEmployee(Employee employee) {
        employees[count] = employee;
        count++;
    }
}
