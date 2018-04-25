package net.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PseudoAsynchronousServer {
    private static int DEFALUT_PORT = 12345;
    // 单例的
    private static ServerSocket server;
    // 线程池，懒汉单例
    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void start() throws IOException {
        start(DEFALUT_PORT);
    }

    public synchronized static void start(int port) throws IOException {
        if (server != null) return;
        try {
            server = new ServerSocket(port);
            System.out.println("启动 ServerSocket，port = " + port);
            // 无限循环监听客户端连接请求
            while (true) {
                // accept() 在没有新的请求的连接的时候会阻塞，直到有新的连接请求才会返回socket
                Socket socket = server.accept();
                // 将 socket 请求交给 FixedThreadPool 去管理调度，实现伪异步IO
                // 而实际上线程处理依然是阻塞的，因为当请求连接数达到FixedThreadPool的大小时，
                // 所有其他连接依然需要等待正在被处理中的 socket 关闭，空闲线程的释放。
                // 当一个线程接收一个socket长连接后，直到发生下面的事件，才会被释放：
                // 1、socket当中有数据可读，否则线程一直阻塞，数据读取完毕后，需要等待Timeout时
                // 长，在这期间如果没有新的请求，关闭连接，释放线程
                // 2、发生空指针或者IO异常
                executor.execute(new ServerHandler(socket));
            }
        } finally {
            if (server != null) {
                server.close();
                System.out.println("服务器已关闭");
            }
        }
    }

    public static void main(String[] args) {
        try {
            // 启动服务器，监听默认端口12345，端口范围 0 ~ 65535(16位)
            Server.start(12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
