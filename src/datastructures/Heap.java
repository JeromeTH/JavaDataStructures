package datastructures;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.Comparator;
import java.util.List;

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



    // Other methods of the Heap class
}
