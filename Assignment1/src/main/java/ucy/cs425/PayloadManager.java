package main.java.ucy.cs425;

import java.util.Random;

/**
 * @author Stepan Zalis
 * created on 2018/10/16
 */
class PayloadManager {

    /**
     * Generating random number in range between 300 to 2000 which simulates random amount of bits
     *
     * @return Random number between 300 and 2000
     */
    private static int generateRandomNumber() {

        final int max = 2000, min = 300;
        final int range = (max - min) + 1;

        Random rand = new Random(System.nanoTime());
        return rand.nextInt(range) + min;
    }

    /**
     * @return Payload representing a string message
     */
    public static String getPayload() {

        long size = generateRandomNumber() * 1024;
        StringBuilder sb = new StringBuilder();

        while (size > 0) {
            sb.append('a');
            size--;
        }
        return sb.toString();
    }
}
