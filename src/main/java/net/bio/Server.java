package net.bio;


import bio.ServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server {
    private static int DEFALUT_PORT = 12345;
    // 单例的
    private static ServerSocket server;

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
                // 从这里可以看出，对于每一传统BIO的请求都需要开启一个新的线程
                // 这样对于高并发的场景，是没有办法满足需要的
                new Thread(new ServerHandler(socket)).start();
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
