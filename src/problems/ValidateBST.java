package problems;
import datastructures.TreeNode;

public class ValidateBST {
    public static TreeNode getMinValueNode(TreeNode node){
        if(node.left == null) return node;
        return getMinValueNode(node.left);
    }

    public static TreeNode getMaxValueNode(TreeNode node){
        if(node.right == null) return node;
        return getMaxValueNode(node.right);
    }

    public boolean isValidBST(TreeNode root) {
        boolean leftok = true;
        boolean rightok = true;
        if(root == null) return true;
        if(root.left != null){
            leftok = ValidateBST.getMaxValueNode(root.left).val < root.val;
        }
        if(root.right != null){
            rightok = ValidateBST.getMinValueNode(root.right).val > root.val;
        }
        return leftok && rightok && isValidBST(root.left) && isValidBST(root.right);
    }
}
