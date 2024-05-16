package datastructures;
import com.sun.jdi.request.ThreadDeathRequest;
import com.sun.source.tree.Tree;
import datastructures.TreeNode;

import java.util.Random;

public class BinarySearchTree {
    TreeNode root;
    public boolean search(TreeNode node, int x){
        if(node == null){
            return false;
        }
        if(node.val == x){
            return true;
        }
        else if(node.val < x){
            return search(node.right, x);
        }
        else{
            return search(node.left, x);
        }
    }

    public TreeNode insert(TreeNode node, int val){
        if(node == null){
            return new TreeNode(val);
        }
        if(node.val > val){
            node.left = insert(node.left, val);
        }else{
            node.right = insert(node.right, val);
        }
        return node;
    }

    public TreeNode getMinValueNode(TreeNode node){
        if(node.left == null) return node;
        return getMinValueNode(node.left);
    }

    public TreeNode getMaxValueNode(TreeNode node){
        if(node.right == null) return node;
        return getMaxValueNode(node.right);
    }

    public TreeNode delete(TreeNode node, int val){
        // return the node that completed the operation
        if(node == null){
            // if value is not in the tree
            return null;
        }
        if(node.val > val){
            node.left = delete(node.left, val);
            return node;
        }else if(node.val < val){
            node.right = delete(node.right, val);
            return node;
        }else{
            // node.val== val means this is the node to be deleted
            if(node.left == null){
                return node.right;
            }else if(node.right == null){
                return node.left;
            }else{
                Random random = new Random();
                int decider = random.nextInt(2);
                if(decider == 0){
                    TreeNode predecessor = getMaxValueNode(node.left);
                    node.val = predecessor.val;
                    return delete(node.left, predecessor.val);
                }else{
                    TreeNode successor = getMinValueNode(node.right);
                    node.val = successor.val;
                    return delete(node.right, successor.val);
                }
            }
        }
    }

}
