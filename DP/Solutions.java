package DP;

public class Solutions {
    public static int fibonacciDP(int n, int[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        return memo[n] = fibonacciDP(n - 1, memo) + fibonacciDP(n - 2, memo);
    }

}
