package data_structure.tree;

public class RecursiveTraverse {

    /**
     * 中序遍历
     *
     * @param treeNode
     */
    public static void inOrderRecursiveTraverse(TreeNode treeNode) {

        if (treeNode.left != null) inOrderRecursiveTraverse(treeNode.left);
        System.out.println(treeNode.name);
        if (treeNode.right != null) inOrderRecursiveTraverse(treeNode.right);
    }
}
