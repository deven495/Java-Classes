package StackUsingArrays;

public class Stack {
    private int arr[];
    private int top;

    public Stack() {
        arr = new int[10];
        top = -1;
    }

    public Stack(int capacity) {
        arr = new int[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public boolean isFull() {
        return size() == arr.length;
    }

    public int peek() {
        return arr[top];
    }

    public void push(int value) throws Exception {
        if (isFull()) {
            throw new Exception("Stack is Full Bhai");
        }
        top++;
        arr[top] = value;
    }

    public int pop() throws Exception {
        if (isEmpty()) {
            throw new Exception("Stack pehle hi Empty hai bhai");
        }
        int value = arr[top];
        ;
        arr[top] = 0;
        top--;
        return value;
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
