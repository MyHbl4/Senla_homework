public class HR extends Employee{
    private String levelEnglish;
    private boolean hire;

    public HR(String name, String sex, String position, int salary, int bonus, String levelEnglish, boolean hire) {
        super(name, sex, position, salary, bonus);
        this.levelEnglish = levelEnglish;
        this.hire = hire;
    }

    public String getLevelEnglish() {
        return levelEnglish;
    }

    public void setLevelEnglish(String levelEnglish) {
        this.levelEnglish = levelEnglish;
    }

    public boolean isHire() {
        return hire;
    }

    public void setHire(boolean hire) {
        this.hire = hire;
    }
}
