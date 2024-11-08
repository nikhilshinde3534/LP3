//package DAA;

import java.util.*;

public class DAA5 {

    // Utility function to print the N-Queens board
    public static void printBoard(List<String[]> solutions) {
        if (solutions.isEmpty()) {
            System.out.println("No solution exists");
            return;
        }

        for (String[] solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    // Main backtracking method to solve the N-Queens problem
    public static List<String[]> solveNQueens(int n, int firstQueenCol) {
        Set<Integer> col = new HashSet<>();
        Set<Integer> posDiag = new HashSet<>();
        Set<Integer> negDiag = new HashSet<>();

        List<String[]> res = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize the board with "."
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }

        // Place the first queen in the first row
        board[0][firstQueenCol] = 'Q';
        col.add(firstQueenCol);
        posDiag.add(0 + firstQueenCol);
        negDiag.add(0 - firstQueenCol);

        // Call the backtracking function starting from the second row
        backtrack(board, 1, n, col, posDiag, negDiag, res);

        return res;
    }

    // Backtracking function to place queens
    private static void backtrack(char[][] board, int row, int n, Set<Integer> col,
                                  Set<Integer> posDiag, Set<Integer> negDiag, List<String[]> res) {
        if (row == n) {
            // If all queens are placed, add the board configuration to the result list
            String[] solution = new String[n];
            for (int i = 0; i < n; i++) {
                solution[i] = new String(board[i]);
            }
            res.add(solution);
            return;
        }

        for (int c = 0; c < n; c++) {
            // Skip columns and diagonals that are already occupied
            if (col.contains(c) || posDiag.contains(row + c) || negDiag.contains(row - c)) {
                continue;
            }

            // Place the queen and mark the column and diagonals
            board[row][c] = 'Q';
            col.add(c);
            posDiag.add(row + c);
            negDiag.add(row - c);

            // Recurse to the next row
            backtrack(board, row + 1, n, col, posDiag, negDiag, res);

            // Backtrack by removing the queen and unmarking the column and diagonals
            board[row][c] = '.';
            col.remove(c);
            posDiag.remove(row + c);
            negDiag.remove(row - c);
        }
    }

    // Example Usage: Place first queen at the given column (e.g., 1 for 8x8 board)
    public static void main(String[] args) {
        int n = 8;  // Size of the board (8x8)
        int firstQueenCol = 1;  // Column for the first queen in the first row
        List<String[]> solutions = solveNQueens(n, firstQueenCol);

        // Print the solutions
        printBoard(solutions);
    }
}
