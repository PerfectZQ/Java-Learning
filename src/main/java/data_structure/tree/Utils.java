package data_structure.tree;


public class Utils {
    /**
     * 计算当前树的最大深度
     * maxDepth = max(left.maxDepth,right.maxDepth)
     *
     * @param treeNode
     * @return
     */
    public static int getMaxDepth(TreeNode treeNode) {
        if (treeNode == null) return 0;
        int leftDepth = getMaxDepth(treeNode.left);
        int rightDepth = getMaxDepth(treeNode.right);
        return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

}
