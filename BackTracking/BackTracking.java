package BackTracking;

public class BackTracking {

    public static void queenPermu(boolean[] boxes, int qpsf, int tq, String ans) {
        if (qpsf == tq) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < boxes.length; i++) {
            if (!boxes[i]) {
                boxes[i] = true;
                queenPermu(boxes, qpsf + 1, tq, ans + "Queen" + qpsf + "Box" + i);
                boxes[i] = false;
            }
        }
    }

    public static void coinChange1(int denom[], int idx, int amount, String ans) {
        if (amount == 0) {
            System.out.println(ans);
            return;
        }
        if (amount < 0 || idx == denom.length) {
            return;
        }
        coinChange1(denom, idx, amount - denom[idx], ans + denom[idx]);
        coinChange1(denom, idx + 1, amount, ans);
    }
}
