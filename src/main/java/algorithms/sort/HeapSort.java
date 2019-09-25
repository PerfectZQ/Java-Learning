package algorithms.sort;

/**
 * 堆排序
 */
public class HeapSort {


    /**
     * 调整为最大堆，从上往下遍历节点，保证当前正在校验的子堆的父节点大于子节点
     *
     * @param seq   要校准的子堆
     * @param start
     */
    private <T extends Comparable<? super T>> void adjustment(T[] seq, int start, int end) {
        while (true) {
            // 父节点下标为i(从0开始)，左子节点下标为 2*i+1
            int leftNode = 2 * start + 1;
            // 没有子节点，即叶子结点
            if (leftNode > end) return;
            // 如果右节点存在，则比较两个节点的大小，选出子节点中最大的节点和父节点进行比较就可以了
            if (leftNode + 1 <= end && seq[leftNode].compareTo(seq[leftNode + 1]) < 0) leftNode++;
            // 如果父节点小于子节点中最大的节点，则进行交换
            if (seq[start].compareTo(seq[leftNode]) < 0) {
                swap(seq, start, leftNode);
                start = leftNode;
            } else return;
        }
    }

    /**
     * 数组内的元素交换
     *
     * @param a
     * @param index1
     * @param index2
     * @return
     */
    private <T extends Comparable<? super T>> T[] swap(T[] a, int index1, int index2) {
        T temp = a[index1];
        a[index1] = a[index2];
        a[index2] = temp;
        return a;
    }


    public <T extends Comparable<? super T>> void heapSort(T[] a) {
        // 初始化最大堆，从第一个有子节点的节点开始从下往上调整
        for (int firstNode = a.length / 2 - 1; firstNode >= 0; firstNode--) adjustment(a, firstNode, a.length - 1);
        // 排序
        for (int index = a.length - 1; index > 0; index--) {
            swap(a, 0, index);
            adjustment(a, 0, index - 1);
        }
    }


    public static void main(String[] args) {
        HeapSort h = new HeapSort();
        Double[] d = {3.0, 2.0, 1.1, 8.0, 9.0, 7.0, 7.0};
        h.heapSort(d);
        System.out.println("结果...");
        for (double num : d) {
            System.out.print(num + ",");
        }
    }

}
