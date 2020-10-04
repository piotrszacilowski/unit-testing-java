public class Test {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();
        System.out.println(areEqual(2, calculator.add("2")));
        System.out.println(areEqual(3, calculator.add("1,2")));
    }

    public static boolean areEqual(int expected, int actual) {
        return actual == expected;
    }
}
