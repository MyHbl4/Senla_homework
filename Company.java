public class Company {
    public static void main(String[] args) {
        int salaryCompany = 0;
        Employee[] comp = new Employee[10];
        comp[0] = new Administration("Mark", "male", "CEO", 15000, 100000);
        comp[1] = new Developer("Alex", "male", "Team Lead", 10000, "Java");
        comp[2] = new Developer("Damian", "male", "Developer", 5000, "Java");
        comp[3] = new Employee("Max", "male", "QA", 4000);
        comp[4] = new Employee("Aleksandr", "male", "Project Manager", 10000);
        comp[5] = new Employee("Sergey", "male", "Business Analyst", 5000);
        comp[6] = new Developer("Misha", "male", "Developer", 3000, "Java");
        comp[7] = new Developer("Evgen", "male", "Developer", 1500, "Java");
        comp[8] = new Employee("Igor", "male", "Designer", 6000);
        comp[9] = new Developer("Sam", "male", "Developer", 500, "Java");
        for (Employee e : comp) {
            salaryCompany += e.getSalary();
        }
        System.out.println("Выплаты работникам за месяц составили: " + salaryCompany);
    }
}
