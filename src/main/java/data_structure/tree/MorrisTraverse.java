package data_structure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * MorrisTraverse的优点就是非递归，因此不需要使用栈，空间复杂度只有O(1)。使用O(1)
 * 的空间复杂度进行遍历，最大的难点在于遍历到子节点的时候如何返回到父节点，因为没有
 * 栈或者队列等辅助空间记录(假设节点没有指向父节点的指针)，为了解决这个问题Morris方
 * 法使用线索二叉树(ThreadedBinaryTree)来解决这个问题。
 */
public class MorrisTraverse {

    /**
     * morris 中序遍历
     * <p>
     * 在中序遍历下，遍历顺序是： 左子节点 -> 根节点 -> 右子节点，
     * 从宏观上看可以看成是： 左子树 -> 根节点 -> 右子树
     * 而一个子树的遍历过程一定是： 最左节点(一定没有左子节点)开始 -> 最右节点(一定没有右子节点)结束
     * 因此中序遍历下:
     * 根节点左子树的最右节点(前驱节点) -> 根节点 -> 根节点右子树的最左节点(后继节点)
     * <p>
     * 当左子树的所有节点都遍历完了，如果没有栈空间记录，就没有办法找到根节点是谁了
     * 如果左子树中最后一个被遍历的节点，即最右节点，它有指向根节点的指针，那么问题
     * 不就解决了吗。这个指针就是所占用的O(1)空间，只需要常数个辅助空间就可以了。
     *
     * @param rootNode
     * @return
     */
    public void morrisInOrder(TreeNode rootNode) {
        TreeNode currentNode = rootNode;
        while (currentNode != null) {
            // 没有左子节点，遍历右子节点
            // 如果当前节点是叶节点，除了初始树的最后一个被遍历的叶节点，都有一个用于
            // 辅助的右子节点，作为指向根节点的指针。因为：如果这个叶节点是上层的节点
            // 的左子节点，它一定是上层节点中序遍历下的前驱节点，如果这个叶节点是上层
            // 节点的右子节点，它一定是上上层节点中序遍历下的前驱节点
            if (currentNode.left == null) {
                System.out.print(currentNode.name + " ");
                currentNode = currentNode.right;
            } else {
                // 找到当前节点中序遍历下的前驱结点
                TreeNode predecessorNode = findMostRightNode(currentNode);
                if (predecessorNode.right == null) {
                    // 添加前驱结点指向当前节点的指针
                    predecessorNode.right = currentNode;
                    // 向左遍历，直到找到子树的最左子节点
                    currentNode = currentNode.left;
                } else {
                    // 前驱节点有指向当前节点的指针，说明当前节点是通过前驱节点的指针返回来的
                    // 左子树已经完全遍历完毕，清空前驱结点的指针，输出当前节点(根节点)，开始
                    // 遍历右子节点
                    predecessorNode.right = null;
                    System.out.print(currentNode.name + " ");
                    currentNode = currentNode.right;
                }
            }
        }
    }


    /**
     * Morris 前序遍历
     * <p>
     * 在前序遍历下，遍历顺序是： 根节点 -> 左子节点 -> 右子节点，
     * 从宏观上看可以看成是： 根节点 -> 左子树 -> 右子树
     * 而一个子树的遍历过程一定是： 根节点开始 -> 最右节点结束
     * 遍历完左子树后，遍历右子树，也是需要左子树最后一个被访问的节点指向根
     * 节点的指针然后去遍历右子树，因此前序遍历和中序遍历类似，只是输出元素
     * 的顺序不同。
     */
    public void morrisPreOrder(TreeNode root) {
        TreeNode currentNode = root;
        while (currentNode != null) {
            // 没有左子节点，遍历右子节点
            if (currentNode.left == null) {
                System.out.print(currentNode.name + " ");
                currentNode = currentNode.right;
            } else {
                // 有左子节点，找到左子树的最右节点
                TreeNode predecessorNode = findMostRightNode(currentNode);
                if (predecessorNode.right == null) {
                    // 当前节点第一次遍历
                    System.out.print(currentNode.name + " ");
                    // 给最右节点指向当前节点的指针
                    predecessorNode.right = currentNode;
                    // 遍历左子节点
                    currentNode = currentNode.left;
                } else {
                    // 当前节点第二次遍历，左子树已经遍历完毕，把最右结点
                    // 指向当前节点的指针清空，开始遍历右子树
                    predecessorNode.right = null;
                    currentNode = currentNode.right;
                }
            }
        }
    }

    /**
     * morris 后序遍历
     * <p>
     * 在后序遍历下，遍历顺序是： 左子节点 -> 右子节点 -> 根节点
     * 从宏观上看可以看成是： 左子树 -> 右子树 -> 根节点
     * <p>
     * 从左子树跳到右子树需要经过根节点，而右子树最后一个被遍历的节点，是上层根节点
     * 左子树的最右节点只能返回上层的根节点，按之前的逻辑是无法返回到本层的根节点了，
     * 所以后序遍历就相对于前序和中序麻烦一点。既然返回的是上层的根节点，那么本层的
     * 根节点不就是上层根节点的左子节点吗，但是新问题又来了，初始根节点的右子树的最
     * 后一个节点遍历完了之后，怎么找到初始根节点？答案就是添加一个辅助根节点，让初
     * 始根节点成为这个辅助节点的左子节点。
     *
     * @param root
     */
    public void morrisPostOrder(TreeNode root) {
        TreeNode tempNode = new TreeNode("tempNode");
        tempNode.left = root;
        TreeNode currentNode = tempNode;
        while (currentNode != null) {
            // 没有左子节点，遍历右子节点
            if (currentNode.left == null) {
                currentNode = currentNode.right;
            } else {
                TreeNode predecessorNode = findMostRightNode(currentNode);
                if (predecessorNode.right == null) {
                    predecessorNode.right = currentNode;
                    currentNode = currentNode.left;
                } else {
                    predecessorNode.right = null;
                    TreeNode tmp = currentNode.left;
                    // 倒序输出当前节点左子节点到当前节点前驱节点路径上的所有节点
                    List<String> nodes = new ArrayList<>();
                    while (tmp != null) {
                        nodes.add(0, tmp.name);
                        tmp = tmp.right;
                    }
                    nodes.forEach(x -> System.out.print(x + " "));
                    // 遍历完左子树，返回到根节点，开始遍历右子树
                    currentNode = currentNode.right;
                }
            }
        }
    }


    /**
     * 找到当前节点左子树的最右结点(当前节点在中序遍历的前驱节点——左子树在中序遍历下最后一个被访问的节点)
     *
     * @param currentNode
     * @return
     */
    public TreeNode findMostRightNode(TreeNode currentNode) {
        TreeNode tmpNode = currentNode.left;
        while (tmpNode.right != null && tmpNode.right != currentNode) {
            tmpNode = tmpNode.right;
        }
        return tmpNode;
    }
}
