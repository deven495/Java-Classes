import java.util.ArrayList;

public class prac {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        int start = 0;
        int end = list.size() - 1;
        System.out.print(list);
        while (start >= end) {
            if (list.get(start) != list.get(end)) {
                System.out.println("false");
            } else {
                start++;
                end--;
            }
        }
        System.out.println("true");

    }
}