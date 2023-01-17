package QueueUsingArrays;

public class Queue {
    int data[];
    int size;
    int front;

    public Queue(int capacity) {// Constructor
        data = new int[capacity];
        size = 0;
        front = 0;
    }

    public Queue() {// Constructor
        data = new int[5];
        size = 0;
        front = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(int value) throws Exception {
        if (size() == data.length) {
            throw new Exception("Queue is Full");
        }
        data[((front + size) % data.length)] = value;
        size++;
    }

    public int deQueue() throws Exception {
        if (isEmpty())
            throw new Exception("Queue is Empty");
        int value = data[front];
        data[front] = 0;
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(data[(i + front) % data.length] + " ");
        }
        System.out.println();
    }

}
