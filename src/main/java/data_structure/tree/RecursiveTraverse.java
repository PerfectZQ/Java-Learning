package data_structure.tree;

/**
 * 前中后序遍历的区别在于，根节点的输出顺序
 */
public class RecursiveTraverse {

    /**
     * 中序遍历: 左子节点 -> 根节点 -> 右子节点
     *
     * @param treeNode
     */
    public static void inOrderRecursiveTraverse(TreeNode treeNode) {
        if (treeNode.left != null) inOrderRecursiveTraverse(treeNode.left);
        System.out.println(treeNode.name);
        if (treeNode.right != null) inOrderRecursiveTraverse(treeNode.right);
    }

    /**
     * 前序遍历: 根节点 -> 左子节点 -> 右子节点
     *
     * @param treeNode
     */
    public static void preOrderRecursiveTraverse(TreeNode treeNode) {
        System.out.println(treeNode.name);
        if (treeNode.left != null) preOrderRecursiveTraverse(treeNode.left);
        if (treeNode.right != null) preOrderRecursiveTraverse(treeNode.right);
    }

    /**
     * 后序遍历: 左子节点 -> 右子节点 -> 根节点
     *
     * @param treeNode
     */
    public static void postOrderRecursiveTraverse(TreeNode treeNode) {
        if (treeNode.left != null) postOrderRecursiveTraverse(treeNode.left);
        if (treeNode.right != null) postOrderRecursiveTraverse(treeNode.right);
        System.out.println(treeNode.name);
    }
}
