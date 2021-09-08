//Написать программу выводящую на экран случайно сгенерированное
// трёхзначное натуральное число и сумму его цифр

public class RandomNum {
    public static void main(String[] args) {
        int randomInt = 0;
        int minThreeDigitNum = 100;
        while (randomInt < minThreeDigitNum) {
            randomInt = (new java.util.Random()).nextInt(999);
        }
        String str = "" + randomInt;
        int one = str.charAt(str.length() - 3) - '0';
        int two = str.charAt(str.length() - 2) - '0';
        int three = str.charAt(str.length() - 1) - '0';
        System.out.println(randomInt);
        System.out.println(one + two + three);
    }
}
