//package DAA;

public class DAA4{

        public static int knapsackDynamicProgramming(int[] weights, int[] values, int capacity) {
            int n = values.length;
            // Create a 2D DP table with dimensions (n+1) x (capacity+1)
            int[][] dp = new int[n + 1][capacity + 1];

            // Build table dp[] in a bottom-up manner
            for (int i = 1; i <= n; i++) {
                for (int w = 1; w <= capacity; w++) {
                    if (weights[i - 1] <= w) {
                        // max of (including the item or excluding it)
                        dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                    } else {
                        // If the weight of the item is more than capacity, exclude it
                        dp[i][w] = dp[i - 1][w];
                    }
                }
            }

            // The maximum value that can be obtained is in dp[n][capacity]
            return dp[n][capacity];
        }

        public static void main(String[] args) {
            int[] weights = {1, 3, 4, 5}; // Weights of items
            int[] values = {1, 4, 5, 7};  // Values of items
            int capacity = 7;             // Knapsack capacity

            int maxValue = knapsackDynamicProgramming(weights, values, capacity);
            System.out.println("Maximum value in Knapsack = " + maxValue);
        }
    }
