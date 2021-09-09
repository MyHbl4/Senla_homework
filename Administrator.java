public class Administrator extends Employee{
    private String assistent;
    private boolean fire;

    public Administrator(String name, String sex, String position, int salary, int bonus, String assistent, boolean fire) {
        super(name, sex, position, salary, bonus);
        this.assistent = assistent;
        this.fire = fire;
    }

    public String getAssistent() {
        return assistent;
    }

    public void setAssistent(String assistent) {
        this.assistent = assistent;
    }
}
