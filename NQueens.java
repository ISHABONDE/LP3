import java.util.Scanner;

public class NQueens {

    static int N;
    static int[] queenCol;
    static boolean solutionFound = false;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of N: ");
        N = sc.nextInt();

        queenCol = new int[N];

        // Initialize all rows as empty (-1 means no queen placed yet)
        for (int i = 0; i < N; i++) {
            queenCol[i] = -1;
        }

        // Take user input for first queen
        System.out.print("Enter row for first queen (0 to " + (N - 1) + "): ");
        int r = sc.nextInt();

        System.out.print("Enter column for first queen (0 to " + (N - 1) + "): ");
        int c = sc.nextInt();

        // Place first queen
        queenCol[r] = c;

        System.out.println("\nPlacing first queen at (" + r + ", " + c + ")");
        System.out.println("Solving for remaining queens...\n");

        solve(0, r);

        if (!solutionFound) {
            System.out.println("No solution exists with this first queen position.");
        }

        sc.close();
    }

    static void solve(int row, int fixedRow) {

        if (row == fixedRow) {
            solve(row + 1, fixedRow);
            return;
        }

        if (row == N) {
            printBoard();
            solutionFound = true;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                queenCol[row] = col;
                solve(row + 1, fixedRow);
                if (solutionFound) return; // stop after first solution
            }
        }

        queenCol[row] = -1; // backtrack
    }

    static boolean isSafe(int row, int col) {
        for (int r = 0; r < N; r++) {

            if (queenCol[r] == -1) continue; // skip rows without queens

            // same column check
            if (queenCol[r] == col)
                return false;

            // diagonal check
            if (Math.abs(r - row) == Math.abs(queenCol[r] - col))
                return false;
        }
        return true;
    }

    static void printBoard() {
        System.out.println("Solution found:\n");

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (queenCol[r] == c)
                    System.out.print("Q ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }

        System.out.println();
    }
}

