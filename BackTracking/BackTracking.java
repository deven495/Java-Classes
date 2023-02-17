package BackTracking;

public class BackTracking {
    private static int count = 0;

    public static void queenPermutations(boolean[] boxes, int qpsf, int tq, String ans) {
        if (qpsf == tq) {
            count++;
            System.out.println(count + "-> " + ans);
            return;
        }
        for (int i = 0; i < boxes.length; i++) {
            if (!boxes[i]) {
                boxes[i] = true;
                queenPermutations(boxes, qpsf + 1, tq, ans + "Queen" + qpsf + "Box" + i);
                boxes[i] = false;
            }
        }
    }

    public static void queenCombinations(boolean[] boxes, int qpsf, int tq, String ans, int lastBox) {
        if (qpsf == tq) {
            count++;
            System.out.println(count + "-> " + ans);
            return;
        }
        for (int i = lastBox + 1; i < boxes.length; i++) {
            if (!boxes[i]) {
                boxes[i] = true;
                queenCombinations(boxes, qpsf + 1, tq, ans + "Queen" + qpsf + "Box" + i + " ", i);
                boxes[i] = false;
            }
        }
    }

    public static void coinChange(int denom[], int idx, int amount, String ans) {
        if (amount == 0) {
            System.out.println(ans);
            return;
        }
        if (amount < 0 || idx == denom.length) {
            return;
        }
        coinChange(denom, idx, amount - denom[idx], ans + denom[idx]);
        coinChange(denom, idx + 1, amount, ans);
    }
}
