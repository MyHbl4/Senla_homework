public class Company {
    private String name;
    private String industry;
    private Employee[] employees;

    public Company(String name, String industry, Employee[] employees) {
        this.name = name;
        this.industry = industry;
        this.employees = employees;
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

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public int salaryAmount(){
        int sum = 0;
        for(Employee e:employees){
            sum += e.getSalary()+e.getBonus();
        }
        return sum;
    }



}
