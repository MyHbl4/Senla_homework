package task3;

public class Runner {
    public static void main(String[] args) {

        Employee[] comp = new Employee[7];
        comp[0] = new Administrator("Mark", "male", "CEO", 15000, 100000, "Lenochka", true);
        comp[1] = new Developer("Alex", "male", "Team Lead", 10000, 1500, "Java");
        comp[2] = new Developer("Damian", "male", "Developer", 5000, 500, "Java");
        comp[3] = new QA("Max", "male", "QA", 4000, 500, true);
        comp[4] = new HR("Misha", "male", "HR", 3000, 3000, "Native", true);
        comp[5] = new Developer("Evgen", "male", "Developer", 1500, 300, "Java");
        comp[6] = new Developer("Sam", "male", "Developer", 500, 1, "Java");

        Company juggernaut = new Company("Jaggernaut", "Transportation", comp);
        System.out.println("Company - " + juggernaut.getName());
        System.out.println("Industry - " + juggernaut.getIndustry());
        System.out.println("Salary - " + juggernaut.salaryAmount());
    }
}
