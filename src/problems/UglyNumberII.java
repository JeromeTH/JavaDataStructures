package problems;

import datastructures.MaxHeap;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class UglyNumberII {
    //1 <= n <= 1690
    //12*12*12 = 1728
    //
    public static void main(String[] args) {
        int n = 1407;
        int answer = new UglyNumberII().nthUglyNumber(n);
        System.out.println(answer);
    }

    public int nthUglyNumber(int n) {
        MaxHeap<Integer> smallest = new MaxHeap<>(Collections.reverseOrder());
        MaxHeap<Integer> largest  = new MaxHeap<>();
        HashMap<Integer, Boolean> vis  = new HashMap<Integer, Boolean>();
        int[] factors = {2, 3, 5};
        smallest.insert(1);
        largest.insert(1);
        vis.put(1, true);
        while(smallest.peek() <= largest.peek()){
            Integer root = smallest.peek();
            smallest.pop();
            for(int j: factors) {
                if(Integer.MAX_VALUE/j <= root || vis.get(root*j) != null) continue;
                vis.put(root*j, true);
                while (largest.size() >= n && largest.peek() > root*j) largest.pop();
                if(largest.size() < n || root* j < largest.peek()) largest.insert(root * j);
                smallest.insert(root * j);
            }
//            System.out.print("smallest:");
//            System.out.println(smallest.size());
//            System.out.print("largest:");
//            System.out.println(largest.size());
        }
        return largest.peek();
    }


}
