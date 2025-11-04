import java.util.Scanner;

public class FibonacciBoth {

    // Recursive Fibonacci function
    static int fibRec(int n) {
        if (n <= 1)
            return n;
        return fibRec(n - 1) + fibRec(n - 2);
    }

    // Iterative Fibonacci series
    static void fibIter(int n) {
        int a = 0, b = 1, c;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        System.out.println("1. Recursive Fibonacci");
        System.out.println("2. Non-Recursive Fibonacci");
        System.out.print("Enter choice: ");
        int ch = sc.nextInt();

        if (ch == 1) {
            for (int i = 0; i < n; i++)
                System.out.print(fibRec(i) + " ");
        } else if (ch == 2) {
            fibIter(n);
        } else {
            System.out.println("Invalid choice");
        }

        sc.close();
    }
}

