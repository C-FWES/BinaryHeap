package BinaryHeap;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class BinaryHeap<E extends Comparable<E>> {
    Comparable  c =null;
    // Parent to find children: 2k + 1 = left, 2k+2 = right
    //Children to find parent: (k-1)/2
    //Requirements: Each step needs to be filled except last step
    //Parent needs to be greater/less than the children
    private ArrayList<E> heapArray = new ArrayList<>();

    private boolean max = true;

    public BinaryHeap(Boolean maxFlag) { //constructor
        this.max = maxFlag;
    }

    public void add(E item) {
        heapArray.add(item);
        swim(heapArray.size() - 1);
    }

    public void addAll(List<E> list) {
        for (E item : list) {
            add(item);
        }
    }

    public E takeRoot() {
        if (heapArray.size() == 0) {
            return null;
        }
        E root = heapArray.get(0);
        E last = heapArray.remove(heapArray.size() - 1);

        if (heapArray.size() == 0) {
            return root;
        }

        heapArray.set(0, last);
        sink(0);
        return root;
    }

    private void swap(int i, int j) {
        E remember = heapArray.get(i);
        heapArray.set(i, heapArray.get(j));
        heapArray.set(j, remember);

    }

    private void swim(int i) {
        E child = heapArray.get(i);
        int parentIndex = (i - 1) / 2;
        E parent = heapArray.get(parentIndex);
        if (shouldSwap(child, parent)) {
            swap(i, parentIndex);
            if (parentIndex > 0) {
                swim(parentIndex);
            }
        }

    }

    private boolean shouldSwap(E child, E parent) {
        return max && child.compareTo(parent) >= 1 || !max && child.compareTo(parent) <= -1;
    }

    private void sink(int i) {
        E parent = heapArray.get(i);
        int leftIndex = 2*i + 1;
        int rightIndex = 2*i + 2;
        int selectedChildIndex = -1;
        E selectedChild = null;
        if (leftIndex > heapArray.size() - 1 && rightIndex > heapArray.size() - 1) {
            return;
        }
        if (rightIndex > heapArray.size() - 1) {
            selectedChildIndex = leftIndex;
            selectedChild = heapArray.get(leftIndex);
        } else {

            E left = heapArray.get(leftIndex);
            E right = heapArray.get(rightIndex);

            if (shouldSwap(left, right)) {
                selectedChildIndex = leftIndex;
                selectedChild = left;
            }
            else {
                selectedChildIndex = rightIndex;
                selectedChild = right;
            }
        }
        if (shouldSwap(selectedChild, parent)) {
            swap(selectedChildIndex, i);
            sink(selectedChildIndex);

        }

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(true);
        binaryHeap.addAll(list);
        while (true) {
            Integer root = binaryHeap.takeRoot();
            if (root != null) {
                System.out.println(root);
            }
            else {
                break;
            }

        }

        List<String> string = new ArrayList<>();
        string.add("apple");
        string.add("appla");
        string.add("bananas apples");
        string.add("ccookies");
        string.add("donuts");
        string.add("gluten free");
        string.add("lime");
        string.add("5");
        BinaryHeap<String> binaryHeapString = new BinaryHeap<String>(false);
        binaryHeapString.addAll(string);
        while (true) {
            String root = binaryHeapString.takeRoot();
            if (root != null) {
                System.out.println(root);
            }
            else {
                break;
            }

        }

    }
}
