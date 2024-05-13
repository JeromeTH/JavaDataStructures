package problems;

import datastructures.MaxHeap;

import java.util.Collection;
import java.util.Collections;

public class KthLargestElement {
    //    Given an integer array nums and an integer k, return the kth largest element in the array.
//
//    Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
//    Can you solve it without sorting?
    public static void main(String[] args) {
        int[] nums = new int[] {3,2,3,1,2,4,5,5,6};
        int k = 4;
        int answer = new KthLargestElement().findKthLargest(nums, k);
        System.out.println(answer);
    }

    public int findKthLargest(int[] nums, int k) {
        // retain a heap that has size k
        // is the kth largest element a certain number?
        MaxHeap<Integer> largest = new MaxHeap<>(Collections.reverseOrder());
        for (int i = 0; i < nums.length; i++) {
            if (largest.size() < k) {
                largest.insert(nums[i]);
            } else {
                if (nums[i] >= largest.peek()) {
                    largest.insert(nums[i]);
                    largest.pop();
                }
            }
        }
        return largest.peek();
    }

}
