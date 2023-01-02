package QueueUsingArrays;

import java.util.NoSuchElementException;

public class QueueUsingArrays {
    private int front;
    private int rear;
    private int[] Queue;
    private int size;

    public QueueUsingArrays(int capacity) {
        Queue = new int[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return Queue.length == 0;
    }

    public int size() {
        return size;
    }

    public void enQueue(int element) throws QueueFullException {
        if (size == Queue.length) {
            throw new QueueFullException();
        }
        if (size == 0) {
            front = 0;
        }
        rear++;
        Queue[rear] = element;
        size++;
        if (size == Queue.length) {
            rear = 0;
        }
    }

    public int deQueue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (size == 0) {
            front = -1;
            rear = -1;
            size = 0;
        }
        int temp = Queue[front];
        size--;
        front++;
        if (front == Queue.length) {
            front = 0;
        }
        return temp;
    }
}
