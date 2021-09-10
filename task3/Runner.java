package task3;

public class Runner {
    public static void main(String[] args) {

        Company juggernaut = new Company("Jaggernaut", "Transportation");
        juggernaut.addEmployee(new Developer("Damian","male", "Developer", 5000, 500, "Java"));
        juggernaut.addEmployee(new Administrator("Mark", "male", "CEO", 15000, 100000, "Lenochka", true));
        juggernaut.addEmployee(new QA("Max", "male", "QA", 4000, 500, true));
        juggernaut.addEmployee(new HR("Misha", "male", "HR", 3000, 3000, "Native", true));
        juggernaut.addEmployee(new Developer("Alex", "male", "Team Lead", 10000, 1500, "Java"));

        System.out.println("Company - " + juggernaut.getName());
        System.out.println("Industry - " + juggernaut.getIndustry());
        System.out.println("Salary - " + juggernaut.salaryAmount());
    }
}
