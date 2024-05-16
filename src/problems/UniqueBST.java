package problems;

public class UniqueBST {
    //Given an integer n, return the number of structurally unique BST's
    // (binary search trees) which has exactly n nodes of unique values from 1 to n.
    int[] vis = new int[19];
    int[] ans = new int[19];
    public int numTrees(int n) {
        if(n == 0 || n == 1){
            vis[n] = 1;
            return 1;
        }
        if(vis[n] == 1) return ans[n];
        int answer = 0;
        for(int i = 1; i <= n; i++){
            answer += numTrees(i - 1) * numTrees(n - i);
        }
        vis[n] = answer;
        return answer;
    }


    public static void main(String[] args){
        int n = 3;
        int answer = new UniqueBST().numTrees(n);
        System.out.println(answer);
    }
}
