public class RandomSum {
    private int sumNum;
    private final int random;

    public RandomSum() {
        this.random = (new java.util.Random()).nextInt(900) + 100;
        this.sumNum = sumRandomNum();
    }

    public int sumRandomNum() {
        String str = "" + random;
        int one = str.charAt(str.length() - 3) - '0';
        int two = str.charAt(str.length() - 2) - '0';
        int three = str.charAt(str.length() - 1) - '0';
        sumNum = one+two+three;
        return sumNum;
    }

    public int getSumNum() {
        return sumNum;
    }

    public int getRandom() {
        return random;
    }


    @Override
    public String toString() {
        return "RandomSum [ "+ "Random number: " + random + ", Sum numbers: " + sumNum +']';
    }
}
