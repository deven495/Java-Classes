package DP;

import java.util.Arrays;

public class DpSolutions {
    public static int fibonacciDP(int n, int[] dp) {
        if (n <= 1) {
            return n;
        }
        if (dp[n] != 0) {
            return dp[n];
        }
        return dp[n] = fibonacciDP(n - 1, dp) + fibonacciDP(n - 2, dp);
    }

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

    public static int pathsHorizontalVertical(int row, int col, int[][] q, int[][] dp) {
        if (row >= q.length || col >= q[0].length || q[row][col] == 1) {
            return 0;
        }
        if (row == q.length - 1 && col == q[0].length - 1) {
            return 1;
        }
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        int ch = pathsHorizontalVertical(row, col + 1, q, dp);
        int cv = pathsHorizontalVertical(row + 1, col, q, dp);

        return dp[row][col] = ch + cv;
    }

    public static int pathsHoriVertiDiagonal(int row, int col, int eRow, int eCol, int[][] dp) {
        if (row == eRow || col == eCol) {
            return 1;
        }
        if (row > eRow && col > eCol) {
            return 0;
        }
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        int ch = pathsHoriVertiDiagonal(row, col + 1, eRow, eCol, dp);
        int cv = pathsHoriVertiDiagonal(row + 1, col, eRow, eCol, dp);
        int cd = pathsHoriVertiDiagonal(row + 1, col + 1, eRow, eCol, dp);

        return dp[row][col] = ch + cv + cd;
    }

    public static int pathsHoriVertiDiagonalBU(int eRow, int eCol) {
        int dp[][] = new int[eRow + 2][eCol + 2];

        for (int cr = eRow; cr >= 0; cr--) {
            for (int cc = eCol; cc >= 0; cc--) {
                if (cr == eRow || cc == eCol) {
                    dp[cr][cc] = 1;
                } else {
                    dp[cr][cc] = dp[cr][cc + 1] + dp[cr + 1][cc] + dp[cr + 1][cc + 1];
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
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

    public static int s2Stringtos1(String s1, String s2) {// TC 3^m+n
        if (s1.length() == 0 || s2.length() == 0) {
            return Math.max(s1.length(), s2.length());
        }
        char c1 = s1.charAt(0);
        char c2 = s2.charAt(0);
        int ans = 0;
        if (c1 == c2) {
            ans = s2Stringtos1(s1.substring(1), s2.substring(1));
        } else {
            int i = s2Stringtos1(s1.substring(1), s2);
            int d = s2Stringtos1(s1, s2.substring(1));
            int r = s2Stringtos1(s1.substring(1), s2.substring(1));

            ans = Math.min(i, Math.min(r, d)) + 1;
        }

        return ans;
    }

    public static int s2Stringtos1TD(String s1, String s2, int vdx1, int vdx2) {
        if (s1.length() == vdx1 || s2.length() == vdx2) {
            return Math.max(s1.length() - vdx1, s2.length() - vdx2);
        }
        char c1 = s1.charAt(vdx1);
        char c2 = s2.charAt(vdx2);
        int ans = 0;
        if (c1 == c2) {
            ans = s2Stringtos1TD(s1, s2, vdx1 + 1, vdx2 + 1);
        } else {
            int i = s2Stringtos1TD(s1, s2, vdx1 + 1, vdx2);
            int d = s2Stringtos1TD(s1, s2, vdx1, vdx2 + 1);
            int r = s2Stringtos1TD(s1, s2, vdx1 + 1, vdx2 + 1);

            ans = Math.min(i, Math.min(r, d)) + 1;
        }

        return ans;
    }

    public static int s2Stringtos1BU(String s1, String s2) {

        int dp[][] = new int[s1.length() + 1][s2.length() + 1];
        for (int i = dp.length - 1; i >= 0; i--) {
            for (int j = dp[i].length - 1; j >= 0; j--) {
                if (i == dp.length - 1) {
                    dp[i][j] = s2.length() - j;
                } else if (j == dp[i].length - 1) {
                    dp[i][j] = s1.length() - i;
                } else {
                    char c1 = s1.charAt(i);
                    char c2 = s2.charAt(j);
                    if (c1 == c2) {
                        dp[i][j] = dp[i + 1][j + 1];
                    } else {
                        int op1 = dp[i][j + 1];
                        int op2 = dp[i + 1][j];
                        int op3 = dp[i + 1][j + 1];

                        dp[i][j] = Math.min(op1, Math.min(op2, op3)) + 1;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[0][0];
    }

    public static int WineProblemRec(int wines[], int si, int ei) {
        if (si == ei) {
            return wines[si] * (wines.length - (ei - si + 1) + 1);
        }
        int start = WineProblemRec(wines, si + 1, ei) + wines[si] * (wines.length - (ei - si + 1) + 1);
        int end = WineProblemRec(wines, si, ei - 1) + wines[ei] * (wines.length - (ei - si + 1) + 1);

        return Math.max(start, end);

    }

    public static int WineProblemBU(int wines[]) {
        int n = wines.length;

        int dp[][] = new int[n][n];

        for (int slide = 0; slide < n; slide++) {
            for (int si = 0; si < n - slide; si++) {
                int ei = si + slide;
                int year = (wines.length - (ei - si + 1) + 1);
                if (si == ei) {
                    dp[si][ei] = wines[si] * year;
                } else {
                    int start = dp[si + 1][ei] + wines[si] * year;
                    int end = dp[si][ei - 1] + wines[ei] * year;

                    dp[si][ei] = Math.max(start, end);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static int friendPairing(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int op1 = friendPairing(n - 1);
        int op2 = (n - 1) * friendPairing(n - 2);
        return op1 + op2;
    }

    public static int matrixMultiWaysRec(int arr[], int si, int ei, int dp[][]) {
        if (ei == si + 1) {
            return 0;
        }
        if (dp[si][ei] != 0) {
            return dp[si][ei];
        }
        int min = Integer.MAX_VALUE;
        for (int k = si + 1; k < ei; k++) {
            int fp = matrixMultiWaysRec(arr, si, k, dp);
            int sp = matrixMultiWaysRec(arr, k, ei, dp);

            int togetherPair = arr[si] * arr[k] * arr[ei];
            int totalPair = fp + sp + togetherPair;
            if (totalPair < min) {
                min = totalPair;
            }
        }
        return dp[si][ei] = min;
    }

    public static int matrixMultiWaysBU(int arr[]) {
        int n = arr.length;

        int dp[][] = new int[n][n];

        for (int slide = 1; slide < n; slide++) {
            for (int si = 0; si < n - slide; si++) {
                int ei = si + slide;
                if (ei == si + 1) {
                    dp[si][ei] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int k = si + 1; k < ei; k++) {
                        int fp = dp[si][k];
                        int sp = dp[k][ei];
                        int togetherPair = arr[si] * arr[k] * arr[ei];
                        int totalPair = fp + sp + togetherPair;
                        if (totalPair < min) {
                            min = totalPair;
                        }
                    }
                    dp[si][ei] = min;
                }

            }
        }
        return dp[0][n - 1];
    }
}
