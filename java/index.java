import java.util.Scanner;

public class PrimeChecker {

    // Метод для перевірки, чи є число простим
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть число: ");
        int number = scanner.nextInt();

        if (isPrime(number)) {
            System.out.println(number + " є простим числом.");
        } else {
            System.out.println(number + " не є простим числом.");
        }

        scanner.close();
    }
}
