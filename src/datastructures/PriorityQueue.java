package datastructures;
import java.util.Comparator;

public class PriorityQueue<E> extends Heap{
    private final Comparator<? super E> comparator;

    public PriorityQueue(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

}
