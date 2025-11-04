import java.util.*;

public class Knapsack01_Selection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Number of items
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int values[] = new int[n];
        int weights[] = new int[n];

        // Take item values in one line
        System.out.print("Enter values of items (space-separated): ");
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        // Take item weights in one line
        System.out.print("Enter weights of items (space-separated): ");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        // Knapsack capacity
        System.out.print("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();

        // DP table
        int dp[][] = new int[n + 1][capacity + 1];

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {

                if (weights[i - 1] > w) {
                    // Item cannot be included
                    dp[i][w] = dp[i - 1][w];
                } else {
                    // max(excluding item, including item)
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                }
            }
        }

        // Maximum value stored at last cell
        int maxValue = dp[n][capacity];
        System.out.println("Maximum value that can be put in knapsack = " + maxValue);

        // ---------------- FIND SELECTED ITEMS ----------------
        System.out.println("Selected items:");

        int w = capacity;
        for (int i = n; i > 0 && maxValue > 0; i--) {

            // If value changes → item was included
            if (maxValue != dp[i - 1][w]) {
                System.out.println("Item " + i + ": Value = " + values[i - 1] + ", Weight = " + weights[i - 1]);

                // Reduce value and capacity
                maxValue -= values[i - 1];
                w -= weights[i - 1];
            }
        }
    }
}

