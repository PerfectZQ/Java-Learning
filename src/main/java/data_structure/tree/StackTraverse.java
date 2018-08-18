package data_structure.tree;

import java.util.Stack;

public class StackTraverse {

    /**
     * 中序遍历
     *
     * @param treeNode
     */
    public static void inOrderTraverse(TreeNode treeNode) {
        if (treeNode == null) return;

        Stack<TreeNode> s = new Stack<>();

        TreeNode currentNode = treeNode;
        s.push(currentNode);

        while (!s.empty()) {
            if (currentNode.left != null) {
                currentNode = currentNode.left;
                s.push(currentNode);
            } else {
                currentNode = s.pop();
                System.out.println(currentNode.name);
                if (currentNode.right != null) {
                    currentNode = currentNode.right;
                    s.push(currentNode);
                }
            }
        }
    }
}
