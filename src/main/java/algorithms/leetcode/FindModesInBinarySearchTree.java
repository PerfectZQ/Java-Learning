package algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树中的查找模式
 * 给定具有重复项的二叉搜索树(BST)，找到给定BST中的所有模式（即最常出现的元素）。
 * <p>
 * {@link <a href="https://leetcode.com/problems/find-mode-in-binary-search-tree/"></a>}
 */
public class FindModesInBinarySearchTree {


    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;

    private int[] modes;

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }

    /**
     * 中序遍历
     *
     * @param root
     */
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        System.out.print(root.val + ",");
        inorder(root.right);
    }

    private void sequenceTraverse(TreeNode root) {
        TreeNode preNode = null;
        TreeNode node = root;
        while (node != null) {
            TreeNode nextNode = null;
            if (node.left != null) {
                System.out.println(node.left.val);
                nextNode = node.left;
            }
            if (node.right != null) {
                System.out.println(node.right.val);
                if (nextNode == null) nextNode = node.right;
            }
            node = nextNode;
        }
    }


    /**
     * 层序遍历
     *
     * @param root
     */
    private void levelTraverse(TreeNode root) {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();//层序遍历时保存结点的队列
        queue.offer(root);//初始化
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");//访问节点
            if (node.left != null)
                queue.offer(node.left);
            if (node.right != null)
                queue.offer(node.right);
        }
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(2);
        root.left.right.right.right = new TreeNode(3);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.right.right.right = new TreeNode(4);
        root.right.right.right.right = new TreeNode(4);
        root.right.right.right.right.right = new TreeNode(4);
        System.out.println(root.left.val);
        FindModesInBinarySearchTree f = new FindModesInBinarySearchTree();
        int[] a = f.findMode(root);
        System.out.println();
        for (int value : a) {
            System.out.print(value + ",");
        }

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
