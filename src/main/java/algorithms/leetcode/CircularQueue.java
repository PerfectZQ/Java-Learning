package algorithms.leetcode;

/**
 * Created by zhangqiang on 2017/5/3.
 *
 * 环形队列实现
 * 队列的特性： FIFO
 */
public class CircularQueue {
    // 用数组实现环形队列
    private Object[] objects;
    // 环形队列大小
    private int size;
    // 环形队列头下标
    private int head;
    // 环形队列尾下标
    private int tail;

    public CircularQueue(int size) {
        this.size = size;
        head = 0;
        tail = 0;
        objects = new Object[size];
    }

    /**
     * 入队列
     * @param o
     * @return
     */
    public Object push(Object o) {
        objects[tail] = o;
        // 环形前进一位
        tail = (tail + 1) % size;
        // 队列已满
        if (tail == head) {
           out();
        }
        return o;
    }

    /**
     * 出队列
     * @return
     */
    public Object out() {
        Object result = objects[head];
        head = (head + 1) % size;
        return result;
    }

    /**
     * 显示
     */
    public void display() {
        int h = head;
        while (h != tail) {
            System.out.println(objects[h]);
            h = (h + 1) % size;
        }
        System.out.println(objects[tail]);

    }
}
