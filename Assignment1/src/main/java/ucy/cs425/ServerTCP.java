package main.java.ucy.cs425;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class representing TCP Server
 * Consumes two arguments: [0] - port; [1] - limit number of requests
 *
 * @author Stepan Zalis
 */
public class ServerTCP {

    private static final int NUMBER_OF_CLIENTS = 10;

    static long startTime;
    static long timeInterval = 1000;

    public static void main(String[] args) {

        resetStartTime();

        // create thread pool for 10 thread
        ExecutorService workerService = Executors.newFixedThreadPool(NUMBER_OF_CLIENTS);

        final int port = Integer.parseInt(args[0]);
        final int numberOfRepetitions = Integer.parseInt(args[1]);

        try {
            ServerSocket socket = new ServerSocket(port);

            while (true) {
                Socket client = socket.accept();
                workerService.submit(new WorkerTCP(client, numberOfRepetitions));
            }

        } catch (IOException io) {
            io.getLocalizedMessage();
        }
    }

    static void resetStartTime() {
        startTime = System.currentTimeMillis();
    }
}
