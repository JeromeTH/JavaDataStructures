package datastructures;
import java.io.Serializable;
import java.util.*;

import java.util.Comparator;

public class Heap<E> {
    private final Comparator<? super E> comparator;
    private List<E> elements;
    private static final int DEFAULT_INITIAL_CAPACITY = 11;
    public Heap() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    public Heap(Comparator<? super E> comparator){
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public Heap(int capacity){
        this(capacity, null);
    }

    public Heap(int capacity, Comparator<? super E> comparator){
        this.comparator = comparator;
        this.elements = new ArrayList<>(capacity);
    }



    private int compare(E e1, E e2) {
        if(comparator != null){
            return comparator.compare(e1, e2);
        }else{
//            if (!(e1 instanceof Comparable)){
//                throw new InvalidPropertiesFormatException("Objects cannot be null");
//            }
//            if (e1 == null || e2 == null) {
//                throw new NullPointerException("Objects cannot be null");
//            }
            @SuppressWarnings("unchecked")
            Comparable<? super E> comparable = (Comparable<? super E>) e1;
            return comparable.compareTo(e2);
        }
    }
    // Other methods of the Heap class
    private void swap(int i, int j) {
        E temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }

    public void insert(E element) {
        elements.add(element); // Add the new element to the end of the list
        siftUp(elements.size() - 1); // Restore the heap property
    }

    private void siftUp(int index) {
        // Get the parent index
        int parentIndex = (index - 1) / 2;

        // Compare the current element with its parent and swap if necessary
        while (index > 0 && compare(elements.get(index), elements.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public E peek(){
        if(elements.isEmpty()){
            return null;
        }
        return elements.get(0);
    }

    public E pop(){
        E top = elements.get(0);
        E last = elements.remove(elements.size() - 1);
        elements.set(0, last);
        siftDown(0);
        return last;
    }

    private void siftDown(int index){
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;
        int minIdx  = index;
        if(leftChildIndex < elements.size() && compare(elements.get(index), elements.get(leftChildIndex)) < 0){
            minIdx = leftChildIndex;
        }
        if(rightChildIndex < elements.size() && compare(elements.get(index), elements.get(rightChildIndex)) < 0){
            minIdx = rightChildIndex;
        }

        if(minIdx != index){
            swap(index, minIdx);
            siftDown(minIdx);
        }
    }
}
