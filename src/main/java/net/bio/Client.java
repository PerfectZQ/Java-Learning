package net.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    // 要请求的端口号
    private static int DEAFULT_PORT = 12345;
    // 要请求的IP地址
    private static String DEFAULT_SERVER_IP = "127.0.0.1";

    public static void sendMessage(String message) {
        send(DEAFULT_PORT, message);
    }

    public static void send(int port, String message) {
        System.out.println("发送消息: " + message);
        // 向服务器建立连接
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            // 向socket写入数据，发送给服务器
            out.println(message);
            // 接受服务器返回的信息
            System.out.println("结果为：" + in.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 启动客户端，连接端口12345，IP 127.0.0.1
        Client.send(12345, "hello world!");
    }
}
