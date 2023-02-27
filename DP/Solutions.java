package DP;

public class Solutions {
    // 1. Cb Solution of Q1
    public static int fibonacciDP(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = fibonacciDP(n - 1, dp) + fibonacciDP(n - 2, dp);
    }

    // 2. Cb Solution of Q2
    public static int dicePossibility(int curr, int end, int[] dp) {
        if (curr == end) {
            return 1;
        }
        if (curr > end) {
            return 0;
        }
        if (dp[curr] != 0) {
            return dp[curr];
        }
        int count = 0;
        for (int dice = 1; dice <= 6; dice++) {
            count += dicePossibility(curr + dice, end, dp);
        }

        return dp[curr] = count;
    }

    // 3.Cb Solution of Q3
    public static int paths(int row, int col, int[][] q, int[][] dp) {
        if (row >= q.length || col >= q[0].length || q[row][col] == 1) {
            return 0;
        }
        if (row == q.length - 1 && col == q[0].length - 1) {
            return 1;
        }
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        int ch = paths(row, col + 1, q, dp);
        int cv = paths(row + 1, col, q, dp);

        return dp[row][col] = ch + cv;
    }

    // 4.Cb Solution of Q4
    public static int LCS(String s1, String s2, int vtx1, int vtx2, int dp[][]) {
        if (s1.length() == vtx1 || s2.length() == vtx2) {
            return 0;
        }
        if (dp[vtx1][vtx2] != -1) {
            return dp[vtx1][vtx2];
        }
        char first = s1.charAt(vtx1);
        char second = s2.charAt(vtx2);
        int ans = 0;
        if (first == second) {
            ans = LCS(s1, s2, vtx1 + 1, vtx2 + 1, dp) + 1;
        } else {
            int op1 = LCS(s1, s2, vtx1 + 1, vtx2, dp);
            int op2 = LCS(s1, s2, vtx1, vtx2 + 1, dp);

            ans = Math.max(op1, op2);
        }
        dp[vtx1][vtx2] = ans;
        return ans;
    }

    public static int LCSBottomUp(String s1, String s2) {
        int dp[][] = new int[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length() - 1; i >= 0; i--) {
            for (int j = s2.length() - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    int op1 = dp[i + 1][j];
                    int op2 = dp[i][j + 1];
                    dp[i][j] = Math.max(op1, op2);
                }
            }
        }
        return dp[0][0];
    }

}
