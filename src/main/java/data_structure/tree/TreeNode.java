package data_structure.tree;

public class TreeNode {
    // 节点名
    String name;
    // 左子节点
    TreeNode left;
    // 右子节点
    TreeNode right;

    public TreeNode(String name) {
        this.name = name;
    }

    /**
     * 输出树形结构
     *
     * @param index
     */
    public void display(int index) {
        for (int i = 0; i < index; i++) {
            System.out.print("---");
        }
        System.out.println("|" + name);
        if (left != null) left.display(index + 1);
        if (right != null) right.display(index + 1);
    }

}
