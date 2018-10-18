package main.java.ucy.cs425;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerTCP {

    static long startTime;
    static long timeInterval = 1000;

    public static void main(String[] args) {

        resetStartTime();

        // create thread pool for 10 thread
        ExecutorService workerService = Executors.newFixedThreadPool(10);

        final int port = Integer.parseInt(args[0]);
        final int numberOfRepetition = Integer.parseInt(args[1]);

        try {
            ServerSocket socket = new ServerSocket(port);

            while (true) {
                Socket client = socket.accept();
                workerService.submit(new WorkerTCP(client, numberOfRepetition));
            }

        } catch (IOException io) {
            io.getLocalizedMessage();
        }
    }

    public static long getStartTime() {
        return startTime;
    }

    public static void resetStartTime() {
        startTime = System.currentTimeMillis();
    }
}
