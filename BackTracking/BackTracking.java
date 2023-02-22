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

    public static void coinChangeCombinations(int denom[], int idx, int amount, String ans) {
        if (amount == 0) {
            count++;
            System.out.println(count + "-> " + ans);
            return;
        }
        if (amount < 0 || idx == denom.length) {
            return;
        }
        coinChangeCombinations(denom, idx, amount - denom[idx], ans + denom[idx]);
        coinChangeCombinations(denom, idx + 1, amount, ans);
    }

    public static void coinChangePermutations(int denom[], int idx, int amount, String ans) {
        if (amount == 0) {
            count++;
            System.out.println(count + "-> " + ans);
            return;
        }
        if (amount < 0 || idx == denom.length) {
            return;
        }
        coinChangePermutations(denom, 0, amount - denom[idx], ans + denom[idx]);
        coinChangePermutations(denom, idx + 1, amount, ans);
    }

    public static void queenCombinationsBoxRespect(boolean[] boxes, int qpsf, int tq, String ans, int lastBox) {
        if (qpsf == tq) {
            count++;
            System.out.println(count + "-> " + ans);
            return;
        }
        if (lastBox == boxes.length) {
            return;
        }

        boxes[lastBox] = true;
        queenCombinationsBoxRespect(boxes, qpsf + 1, tq, ans + "Box" + lastBox + " ", lastBox + 1);
        boxes[lastBox] = false;

        queenCombinationsBoxRespect(boxes, qpsf, tq, ans, lastBox + 1);

    }
}
