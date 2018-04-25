package algorithms.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        for (int i = 1; i <= arr.length; i++) {
            // 如果一次循环中没有发生元素交换，则证明排序完毕
            boolean isStop = true;
            for (int j = 0; j < arr.length - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    isStop = false;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (isStop) {
                System.out.println("no swap, stop");
                break;
            }
            // 输出当次循环结果
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 1, 3, 5, 4, 2};
        sort(arr);

    }
}
