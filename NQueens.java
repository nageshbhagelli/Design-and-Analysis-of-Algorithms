import java.util.Scanner;

public class NQueens {
    static int N;
    static char[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of N : ");
        N = scanner.nextInt();
        board = new char[N][N];

        // Initialize board with '.'
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '.';
            }
        }

        System.out.println("\nAll valid N-Queens placements:\n");
        solve(0);
    }

    static void solve(int row) {
        if (row == N) {
            printBoard();
            System.out.println();
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 'Q';
                solve(row + 1);
                board[row][col] = '.'; // Backtrack
            }
        }
    }

    static boolean isSafe(int row, int col) {
        // Check column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    static void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
