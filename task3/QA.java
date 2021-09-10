package task3;

public class QA extends Employee {
    private boolean automation;

    public QA(String name, String sex, String position, int salary, int bonus, boolean automation) {
        super(name, sex, position, salary, bonus);
        this.automation = automation;
    }

    public boolean isAutomation() {
        return automation;
    }

    public void setAutomation(boolean automation) {
        this.automation = automation;
    }
}
