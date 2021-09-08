public class Developer extends Employee{
    private String language;

    public Developer(String name, String sex, String position, int salary, String language) {
        super(name, sex, position, salary);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
