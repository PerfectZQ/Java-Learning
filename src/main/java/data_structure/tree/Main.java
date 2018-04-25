package data_structure.tree;

public class Main {
    public static void main(String[] args) {
        TreeNode root = new TreeNode("root");
        TreeNode node1 = new TreeNode("node1");
        TreeNode node2 = new TreeNode("node2");
        TreeNode node3 = new TreeNode("node3");
        TreeNode node4 = new TreeNode("node4");
        TreeNode node5 = new TreeNode("node5");
        TreeNode node6 = new TreeNode("node6");
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        root.display(0);
        System.out.println();

        System.out.println("depth = " + Utils.getMaxDepth(root));
        System.out.println();

        MorrisTraverse m = new MorrisTraverse();
        System.out.println("Morris in-order traverse ...");
        m.morrisInOrder(root);
        System.out.println();

        System.out.println("\nMorris pre-order traverse ...");
        m.morrisPreOrder(root);
        System.out.println();

        System.out.println("\nMorris post-order traverse ...");
        m.morrisPostOrder(root);
        System.out.println();

    }
}
