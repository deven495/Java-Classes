import StackUsingArrays.Stack;

public class prac {

    public static void main(String[] args) throws Exception {
        Stack s = new Stack();
        for (int i = 1; i <= 10; i++) {
            s.push(i);
        }
        // s.print();
        for (int i = 0; i < 10; i++) {
            s.print();
            s.pop();
        }
    }
}