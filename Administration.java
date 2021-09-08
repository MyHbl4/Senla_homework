public class Administration extends Employee{
    private int bonus;

    public Administration(String name, String sex, String position, int salary, int bonus) {
        super(name, sex, position, salary);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public int getSalary() {
        return super.getSalary()+bonus;
    }

}
