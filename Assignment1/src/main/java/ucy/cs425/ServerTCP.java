package main.java.ucy.cs425;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {

    static long startTime = System.currentTimeMillis();
    static long timeInterval = 1000;

    public static void main(String[] args) {

        final int port = Integer.parseInt(args[0]);
        final int numberOfRepetition = Integer.parseInt(args[1]);

        try {
            ServerSocket socket = new ServerSocket(port);

            while (true) {

                Socket client = socket.accept();

                // TODO: create multithreaded scheduler
            }

        } catch (IOException io) {
            io.getLocalizedMessage();
        }

    }
}
