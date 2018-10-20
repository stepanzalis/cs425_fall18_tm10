package main.java.ucy.cs425;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class simulates at minimum of 10 tcp clients who establishes a connection
 * with a server hosted on a virtual machine and requesting data from him. It is implemented using
 * multithreading code. Every tcp client is a thread and clients are running concurrently and every
 * client generate a series of requests towards the server.The client sends a hello message which comprises of
 * "hello" string the client's ip and the port and the client id.
 *
 * @author 
 */

public class TCPClient {

    public static long id = 1;
    static private String serverIp;
    static int port = 0;

    /**
     * Inner class Client which extends threads and implements runnable interface for multithreading
     */
    static class Client extends Thread implements Runnable {

        /**
         * When an object implementing interface Runnable is used to create a thread, starting the
         * thread causes the object's run method to be called in that separately executing thread.
         * The general contract of the method run is that it may take any action whatsoever.
         */
        @Override
        public void run() {
            try {

                String response;
                String message;
                long[] latencyTable = new long[300];

                long sentTime;
                long receiveTime;
                int count = 0;

                Socket socket = new Socket(serverIp, port);

                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                BufferedReader server = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );


                final Lock mutex = new ReentrantLock(true);

                mutex.lock();

                // protect code using a mutex because of multithreading (protect clients id variable)
                output.write((int) id);
                long prID = id;
                id++;

                mutex.unlock();

                while (true) {

                    message = "HELLO " + socket.getInetAddress() + " " + socket.getPort() + " " + prID + System.lineSeparator();

                    sentTime = System.nanoTime();
                    output.writeBytes(message);

                    response = server.readLine();
                    receiveTime = System.nanoTime();

                    if (response.equals("ByeBye!")) {

                        socket.close();
                        System.out.println(Arrays.toString(latencyTable));
                        int x = 0;
                        long sum = 0;
                        while (latencyTable[x] != 0) {
                            sum = sum + latencyTable[x];
                            x++;
                            if (x == 300)
                                break;
                        }

                        System.out.println("The Sum Latency is: " + sum / 1000000);
                        System.out.println("The Average Latency is: " + (sum / x) / 1000000);


                        break;
                    }
                    System.out.println(response);
                    latencyTable[count] = receiveTime - sentTime;
                    count++;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Main method reading and parsing from command line and starts the simulation
     * To run the program execute java TCPClient _server's IP_ _Port_
     *
     * @param args arguments from command lines
     */
    public static void main(String args[]) {

        final int NUMBER_OF_CLIENTS = 10;

        serverIp = (args[0]);
        port = Integer.parseInt(args[1]);

        for (int i = 0; i <= NUMBER_OF_CLIENTS; i++) {
            Client client = new Client();
            client.start();
        }
    }
}
