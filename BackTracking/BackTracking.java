package BackTracking;

public class BackTracking {

    public void queenPermu(boolean[] boxes, int qpsf, int tq, String ans) {
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
}
