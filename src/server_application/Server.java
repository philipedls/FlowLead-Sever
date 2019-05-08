package server_application;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    public static void main (String[] args){

        try {
            ServerSocket serverSocket = new ServerSocket(7979);
            Executor executor = Executors.newCachedThreadPool();
            System.out.println("Server ONLINE: " + serverSocket.toString());

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("Client CONNECTED: " + socket.toString());
                executor.execute(new StreamRunnable(socket));
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
