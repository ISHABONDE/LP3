import java.util.*;

// Item class to store details of each item
class Item {
    String id;          // Item name/ID (A, B, C...)
    int profit;         // Profit of item
    int weight;         // Weight of item
    double ratio;       // Profit/Weight ratio (important for greedy choice)
}

public class FractionalKnapsack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take number of items
        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();

        // Create an array of items
        Item[] items = new Item[n];

        // Taking input for each item
        for (int i = 0; i < n; i++) {

            items[i] = new Item(); // create new item object

            System.out.print("\nEnter ID for item " + (i + 1) + ": ");
            items[i].id = sc.next();      // read name like A, B, C

            System.out.print("Enter profit: ");
            items[i].profit = sc.nextInt();   // read profit

            System.out.print("Enter weight: ");
            items[i].weight = sc.nextInt();    // read weight

            // Calculate profit/weight ratio (this decides priority)
            items[i].ratio = (double) items[i].profit / items[i].weight;
        }

        // Enter capacity of knapsack (how much weight we can carry)
        System.out.print("\nEnter knapsack capacity: ");
        int capacity = sc.nextInt();

        // ----------------------------------------------------
        // SORT ITEMS BY RATIO (Profit/Weight) IN DESCENDING ORDER
        // Highest ratio item must be picked first (Greedy strategy)
        // ----------------------------------------------------
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {

                // If current item has smaller ratio, swap
                if (items[i].ratio < items[j].ratio) {
                    Item temp = items[i];
                    items[i] = items[j];
                    items[j] = temp;
                }
            }
        }

        // Variable to store final answer
        double totalProfit = 0;

        // Display table heading
        System.out.println("\nItems Taken in Knapsack:");
        System.out.println("------------------------------------------------------");
        System.out.println("ID | Weight Taken | Fraction | Profit Gained");
        System.out.println("------------------------------------------------------");

        // ----------------------------------------------------
        // Greedy Filling of Knapsack
        // ----------------------------------------------------
        for (int i = 0; i < n; i++) {

            // If bag is full → stop
            if (capacity == 0)
                break;

            // CASE 1: Full item can fit inside knapsack
            if (capacity >= items[i].weight) {

                capacity -= items[i].weight;      // reduce capacity
                totalProfit += items[i].profit;   // add full profit

                System.out.printf("%s  |     %d       |   1.00    |     %d\n",
                        items[i].id, items[i].weight, items[i].profit);
            }

            // CASE 2: Only a fraction of item fits
            else {
                double frac = (double) capacity / items[i].weight; // fraction taken
                double p = items[i].profit * frac;                 // profit gained

                System.out.printf("%s  |     %.2f     |   %.2f    |     %.2f\n",
                        items[i].id, (items[i].weight * frac), frac, p);

                totalProfit += p; // add fractional profit
                capacity = 0;     // bag becomes full
            }
        }

        System.out.println("------------------------------------------------------");
        System.out.println("Maximum Profit = " + totalProfit);
        System.out.println("------------------------------------------------------");
    }
}
3