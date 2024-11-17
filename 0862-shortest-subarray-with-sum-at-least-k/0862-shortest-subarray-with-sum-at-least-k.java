import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefixSum = new long[n + 1];
        
        // Calculate prefix sum
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        Deque<Integer> deque = new LinkedList<>();
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            // Remove indices from the front if subarray sum >= k
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                minLength = Math.min(minLength, i - deque.pollFirst());
            }
            
            // Remove indices from the back to maintain monotonicity
            while (!deque.isEmpty() && prefixSum[i] <= prefixSum[deque.peekLast()]) {
                deque.pollLast();
            }
            
            deque.offerLast(i); // Add current index
        }
        
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test cases
        System.out.println(sol.shortestSubarray(new int[]{1}, 1)); // Output: 1
        System.out.println(sol.shortestSubarray(new int[]{1, 2}, 4)); // Output: -1
        System.out.println(sol.shortestSubarray(new int[]{2, -1, 2}, 3)); // Output: 3
    }
}
