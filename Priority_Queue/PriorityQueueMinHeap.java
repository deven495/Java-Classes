package Priority_Queue;

import java.util.ArrayList;

public class PriorityQueueMinHeap<T> {
    private ArrayList<Element<T>> heap;

    public PriorityQueueMinHeap() {
        heap = new ArrayList<>();
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return heap.size();
    }

    public T getMin() throws HeapEmptyException {
        if (heap.size() == 0) {
            throw new HeapEmptyException();
        }
        return heap.get(0).value;
    }

    public void insert(T value, int priority) {
        Element<T> child = new Element<>(value, priority);
        heap.add(child);
        int childIndex = heap.size() - 1;
        int parentIndex = (childIndex - 1) / 2;

        while (childIndex > 0) {
            if (heap.get(parentIndex).priority > heap.get(childIndex).priority) {
                Element<T> temp = heap.get(childIndex);
                heap.set(childIndex, heap.get(parentIndex));
                heap.set(parentIndex, temp);
                childIndex = parentIndex;
                parentIndex = (childIndex - 1) / 2;
            } else {
                return;
            }
        }
    }

    public T removeMin() throws HeapEmptyException {

        if (heap.size() == 0) {
            throw new HeapEmptyException();
        }
        T element = heap.get(0).value;
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        int parentIndex = 0;
        int child1 = 2 * parentIndex + 1;
        int child2 = 2 * parentIndex + 2;
        int minIndex = parentIndex;
        while (child1 < heap.size()) {
            if (heap.get(child1).priority < heap.get(minIndex).priority) {
                minIndex = child1;
            }
            if (child2 < heap.size() && heap.get(child2).priority < heap.get(minIndex).priority) {
                minIndex = child2;
            }
            if (minIndex == parentIndex) {
                break;
            }
            Element<T> temp = heap.get(minIndex);
            heap.set(minIndex, heap.get(parentIndex));
            heap.set(parentIndex, temp);
            parentIndex = minIndex;
            child1 = 2 * parentIndex + 1;
            child2 = 2 * parentIndex + 2;
        }

        return element;

    }

    public int deleteMinInPlace(int arr[], int size) {
        int deletedEl = arr[0];
        arr[0] = arr[size - 1];
        size--;
        int parentIndex = 0;
        int childLeft = 2 * parentIndex + 1;
        int childRight = 2 * parentIndex + 2;
        int minIndex = parentIndex;
        while (childLeft < size) {
            if (arr[childLeft] < arr[minIndex]) {
                minIndex = childLeft;
            }
            if (arr[childRight] < arr[minIndex]) {
                minIndex = childRight;
            }
            if (minIndex == parentIndex) {
                break;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[parentIndex];
            arr[parentIndex] = temp;
            parentIndex = minIndex;
            childLeft = 2 * parentIndex + 1;
            childRight = 2 * parentIndex + 2;
        }
        return deletedEl;
    }

    public void insertInPlace(int arr[], int i) {

        int childIndex = i;
        int parentIndex = (childIndex - 1) / 2;
        while (childIndex > 0) {
            if (arr[parentIndex] > arr[childIndex]) {
                int temp = arr[parentIndex];
                arr[parentIndex] = arr[childIndex];
                arr[childIndex] = temp;
                childIndex = parentIndex;
                parentIndex = (childIndex - 1) / 2;
            } else {
                return;
            }
        }

    }

}
