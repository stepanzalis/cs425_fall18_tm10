package main.java.ucy.cs425;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Stepan Zalis
 * created on 2018/10/16
 */
public class WorkerTCP implements Runnable {

    private final Socket client;
    private String buffer;
    private int clientId;
    private int limit;


    WorkerTCP(Socket client, int limit) {
        this.client = client;
        this.limit = limit;
        this.buffer = "";
    }

    /**
     * Main running method
     */
    @Override
    public void run() {

        try {

            DataOutputStream output = new DataOutputStream(client.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));

            clientId = reader.read();
            buffer = reader.readLine();

            repeat(reader, output);

        } catch (IOException e) {
            e.getLocalizedMessage();
        }

    }

    /**
     * Repeat this method until buffer reach end
     * @param reader [DataOutputStream]
     * @param output [BufferedReader]
     */
    private void repeat(BufferedReader reader, DataOutputStream output) {

        RequestCounter counter = new RequestCounter();

        while (buffer != null) {

            try {
                output.writeBytes("Welcome " +
                        clientId +  " " +
                        client.getInetAddress() + " " +
                        PayloadManager.getPayload() +
                        System.lineSeparator());

                counter.incrementCounter();
                counter.incrementEnd();

                checkTime(counter);

                buffer = reader.readLine();

                // limit is the same as actual count
                if (counter.getEndCount() == limit) {
                    output.writeBytes("ByeBye!" + System.lineSeparator());
                    break;
                }

                counter.incrementNumberOfRequests();

            } catch (IOException e) {
                e.getLocalizedMessage();
            }
        }
    }

    /**
     * @param counter Reference to [RequestCounter] used as object holding data
     *                about each request
     */
    private void checkTime(RequestCounter counter) {

        if ((System.currentTimeMillis() - ServerTCP.startTime) >= ServerTCP.timeInterval) {

            counter.setSum();
            counter.incrementCount();
            counter.print();
            counter.resetCounter();

            ServerTCP.resetStartTime();
        }
    }

}
