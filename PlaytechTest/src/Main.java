import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter amount of money:");
        //Read input amount of money
        final BigDecimal amountOfMoney = scanner.nextBigDecimal();
        System.out.println("People count:");
        //Read input people count
        final int peopleCount = scanner.nextInt();


        //List of values how much each person should receive
        List<BigDecimal> list = new ArrayList<>();

        list = addNumbersInList(amountOfMoney, peopleCount);

        //Check if the money is divided by the number of people with a remainder
        if (!(amountOfMoney.remainder(BigDecimal.valueOf(peopleCount)).compareTo(BigDecimal.ZERO) == 0)) {
            final BigDecimal sum = sumNumbers(list);

            replaceNumberLower(amountOfMoney, list, sum, amountOfMoney.compareTo(sum) > 0);
        }

        print(list);
    }

    private static void replaceNumberLower(BigDecimal amountOfMoney, List<BigDecimal> list, BigDecimal sum, boolean shouldAdd) {
        BigDecimal numberToAdd = BigDecimal.valueOf(0.01);
        if (!shouldAdd) {
            numberToAdd = BigDecimal.valueOf(-0.01);
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            BigDecimal currentNum = list.get(i);
            currentNum = currentNum.add(numberToAdd);
            list.set(i, currentNum);
            sum = sum.add(numberToAdd);
            if (amountOfMoney.compareTo(sum) == 0) {
                break;
            }
        }
    }

    private static BigDecimal sumNumbers(List<BigDecimal> list) {
        BigDecimal sum = BigDecimal.valueOf(0);

        for (BigDecimal num : list) {
            sum = sum.add(num);
        }
        return sum;
    }

    private static void print(List<BigDecimal> list) {
        System.out.println("Result:");
        for (int i = 0; i < list.size(); i++) {
            if (list.size() - 1 == i) {
                System.out.print(list.get(i));
            } else {
                System.out.print(list.get(i) + ", ");
            }
        }
    }

    private static List<BigDecimal> addNumbersInList(BigDecimal amountOfMoney, int peopleCount) {
        List<BigDecimal> addNumbers = new ArrayList<>();
        BigDecimal divide = amountOfMoney.divide(BigDecimal.valueOf(peopleCount), 2, RoundingMode.HALF_UP);
        for (int i = 0; i < peopleCount; i++) {
            addNumbers.add(divide);
        }
        return addNumbers;
    }
}
