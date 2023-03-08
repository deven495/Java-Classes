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
        // System.out.println(Arrays.deepToString(dp));
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

    public static int knapSackRec(int[] weight, int[] price, int capacity, int idx) {
        if (idx == weight.length || capacity < weight[idx] || capacity == 0) {
            return 0;
        }

        int notTaken = knapSackRec(weight, price, capacity, idx + 1);
        int taken = knapSackRec(weight, price, capacity - weight[idx], idx + 1) + price[idx];

        return Math.max(taken, notTaken);
    }

    public static int knapSackTD(int[] weight, int[] price, int capacity, int idx, int[][] dp) {
        if (idx == weight.length || capacity < weight[idx] || capacity == 0) {
            return 0;
        }
        if (dp[idx][capacity] != 0) {
            return dp[idx][capacity];
        }
        int notTaken = knapSackTD(weight, price, capacity, idx + 1, dp);
        int taken = knapSackTD(weight, price, capacity - weight[idx], idx + 1, dp) + price[idx];

        return dp[idx][capacity] = Math.max(taken, notTaken);
    }

    public static int mixturesRec(int colors[], int si, int ei) {// 40 60 20 50
        if (si == ei) {
            return 0;
        }
        int minSmoke = Integer.MAX_VALUE;
        for (int k = si; k < ei; k++) {
            int fp = mixturesRec(colors, si, k);
            int bp = mixturesRec(colors, k + 1, ei);
            int sw = colorMixtureHelper(colors, si, k) * colorMixtureHelper(colors, k + 1, ei);
            int total = fp + bp + sw;
            minSmoke = Math.min(minSmoke, total);
        }
        return minSmoke;

    }

    public static int mixturesTD(int colors[], int si, int ei, int dp[][]) {// 40 60 20 50
        if (si == ei) {
            return 0;
        }
        if (dp[si][ei] != -1) {// smoke can be zero thats why use -1
            return dp[si][ei];
        }
        int minSmoke = Integer.MAX_VALUE;
        for (int k = si; k < ei; k++) {
            int fp = mixturesTD(colors, si, k, dp);
            int bp = mixturesTD(colors, k + 1, ei, dp);
            int sw = colorMixtureHelper(colors, si, k) * colorMixtureHelper(colors, k + 1, ei);
            int total = fp + bp + sw;
            minSmoke = Math.min(minSmoke, total);
        }
        return dp[si][ei] = minSmoke;

    }

    public static int mixturesBU(int colors[]) {
        int n = colors.length;
        int dp[][] = new int[n][n];

        for (int slide = 0; slide <= n - 1; slide++) {
            for (int si = 0; si <= n - slide - 1; si++) {
                int ei = si + slide;

                if (si == ei) {
                    dp[si][ei] = 0;
                } else {
                    int minSmoke = Integer.MAX_VALUE;
                    for (int k = si; k < ei; k++) {
                        int fp = dp[si][k];
                        int bp = dp[k + 1][ei];
                        int sw = colorMixtureHelper(colors, si, k) * colorMixtureHelper(colors, k + 1, ei);
                        int total = fp + bp + sw;
                        minSmoke = Math.min(minSmoke, total);
                    }
                    dp[si][ei] = minSmoke;
                }
            }
        }
        return dp[0][n - 1];
    }

    private static int colorMixtureHelper(int arr[], int si, int ei) {

        int sum = 0;
        for (int i = si; i <= ei; i++) {
            sum += arr[i];
        }
        return sum % 100;
    }

    public static int rodCuttingRec(int rods[], int idx) {// {0,3,5,8,9,10,17,17,20}
        int max = rods[idx];
        int left = 1;
        int right = idx - 1;
        while (left <= right) {
            int fp = rodCuttingRec(rods, left);
            int bp = rodCuttingRec(rods, right);
            int total = fp + bp;
            max = Math.max(max, total);
            left++;
            right--;
        }
        return max;
    }

    public static int rodCuttingRec2(int rods[], int length) {//
        // {0,3,5,8,9,10,17,17,20}
        if (length == 1) {
            return rods[1];
        }
        if (length == 0) {
            return 0;
        }
        int max = rods[length];
        for (int i = 1; i < length; i++) {
            int fp = rodCuttingRec2(rods, i);
            int bp = rodCuttingRec2(rods, length - i);
            int total = fp + bp;
            max = Math.max(max, total);
        }
        return max;
    }

    private static boolean isPaliHelper(String str, int si, int ei) {
        int l = si;
        int r = ei;
        while (l <= r) {
            if (str.charAt(l) != str.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }

    public static int palindromicPartioning(String str, int si, int ei) {// some error in the code
        if (isPaliHelper(str, si, ei)) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int i = si; i < ei; i++) {
            int fp = palindromicPartioning(str, si, i);
            int bp = palindromicPartioning(str, i + 1, ei);
            min = Math.min(min, fp + bp + 1);
        }
        return min;
    }

    public static boolean[][] palli(String str) {
        boolean[][] palliChachu = new boolean[str.length()][str.length()];
        for (boolean[] x : palliChachu) {
            Arrays.fill(x, true);
        }
        for (int row = str.length() - 2; row >= 0; row--) {
            for (int col = row + 1; col < str.length(); col++) {
                if (str.charAt(row) == str.charAt(col)) {
                    palliChachu[row][col] = palliChachu[row + 1][col - 1];
                } else {
                    palliChachu[row][col] = false;
                }
            }
        }
        return palliChachu;
    }

    public static int partyBU(String str) {
        int dp[][] = new int[str.length()][str.length()];
        boolean[][] palliChachu = palli(str);
        for (int k = 0; k <= str.length() - 1; k++) {
            for (int si = 0; si <= str.length() - k - 1; si++) {
                int ei = si + k;
                if (palliChachu[si][ei]) {
                    dp[si][ei] = 0;
                } else {
                    int min = Integer.MAX_VALUE;
                    for (int i = si; i < ei; i++) {
                        int fp = dp[si][i];
                        int bp = dp[i + 1][ei];
                        min = Math.min(min, fp + bp + 1);
                    }
                    dp[si][ei] = min;
                }
            }
        }
        return dp[0][str.length() - 1];
    }

    public static int LPSTD(String str, int si, int ei, int dp[][]) {
        if (si > ei) {
            return 0;
        }
        if (si == ei) {
            return 1;
        }
        if (dp[si][ei] != 0) {
            return dp[si][ei];
        }
        int ans = 0;
        if (str.charAt(si) == str.charAt(ei)) {
            ans = LPSTD(str, si + 1, ei - 1, dp) + 2;
        } else {
            int op1 = LPSTD(str, si + 1, ei, dp);
            int op2 = LPSTD(str, si, ei - 1, dp);
            ans = Math.max(op1, op2);
        }
        return dp[si][ei] = ans;
    }

    public static int LPSBU(String str) {
        int n = str.length();
        int dp[][] = new int[n][n];

        for (int slide = 0; slide < n; slide++) {
            for (int si = 0; si < n - slide; si++) {
                int ei = si + slide;
                if (si == ei) {
                    dp[si][ei] = 1;
                } else {
                    int ans = 0;
                    if (str.charAt(si) == str.charAt(ei)) {
                        ans = dp[si + 1][ei - 1] + 2;
                    } else {
                        int op1 = dp[si + 1][ei];
                        int op2 = dp[si][ei - 1];
                        ans = Math.max(op1, op2);
                    }
                    dp[si][ei] = ans;
                }

            }
        }
        return dp[0][n - 1];
    }

    public static int kOrderedLCS(String str1, String str2, int k, int i, int j, int[][][] dp) {
        if (str1.length() == i || str2.length() == j) {
            return 0;
        }
        if (dp[k][i][j] != 0) {
            return dp[k][i][j];
        }
        int ans = 0;
        if (str1.charAt(i) == str2.charAt(j)) {
            ans = kOrderedLCS(str1, str2, k, i + 1, j + 1, dp) + 1;
        } else {
            int op1 = kOrderedLCS(str1, str2, k, i + 1, j, dp);
            int op2 = kOrderedLCS(str1, str2, k, i, j + 1, dp);
            int op3 = 0;
            if (k > 0)
                op3 = kOrderedLCS(str1, str2, k - 1, i + 1, j + 1, dp) + 1;
            ans = Math.max(op1, Math.max(op2, op3));
        }
        return dp[k][i][j] = ans;
    }

    public static int LCSin3Strings(String str1, String str2, String str3, int i, int j, int k, int dp[][][]) {
        if (str1.length() == i || str2.length() == j || str3.length() == k) {
            return 0;
        }
        if (dp[i][j][k] != 0) {
            return dp[i][j][k];
        }
        int ans;
        if (str1.charAt(i) == str2.charAt(j) && str2.charAt(j) == str3.charAt(k)) {
            ans = LCSin3Strings(str1, str2, str3, i + 1, j + 1, k + 1, dp) + 1;
        } else {
            int op1 = LCSin3Strings(str1, str2, str3, i + 1, j, k, dp);
            int op2 = LCSin3Strings(str1, str2, str3, i, j + 1, k, dp);
            int op3 = LCSin3Strings(str1, str2, str3, i, j, k + 1, dp);
            ans = Math.max(op1, Math.max(op2, op3));
        }
        return dp[i][j][k] = ans;
    }
}
