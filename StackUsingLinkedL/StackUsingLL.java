package StackUsingLinkedL;

public class StackUsingLL<T> {
    int size;
    Node<T> head;

    public StackUsingLL() {
        size = 0;
        head = null;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public T top() throws StackEmptyException {
        if (size == 0) {
            throw new StackEmptyException();
        }
        return head.val;
    }

    public void push(T element) {

        Node<T> n = new Node<T>(element);
        n.next = head;
        head = n;
        size++;
    }

    public T pop() throws StackEmptyException {
        if (size == 0) {
            throw new StackEmptyException();
        }
        T previous = head.val;
        head = head.next;
        size--;
        return previous;
    }

    public void print() {
        Node<T> temp;
        temp = head;
        for (int i = 0; i < size; i++) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
}
