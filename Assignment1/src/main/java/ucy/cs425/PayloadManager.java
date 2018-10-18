package main.java.ucy.cs425;

import java.util.Random;

/**
 * @author Stepan Zalis
 * created on 2018/10/16
 */
class PayloadManager {

    /**
     * Generating random number in range between min to max which simulates random amount of bits
     *
     * @param min Minimum number to generate from
     * @param max Maximum number to generate from
     *
     * @return Pseudo random number
     */
    private static int generateRandomNumberBetween(int min, int max) {

        final int range = (max - min) + 1;

        Random rand = new Random(System.nanoTime());
        return rand.nextInt(range) + min;
    }

    /**
     * @return Payload representing a string message
     */
    public static String getPayload() {

        long size = generateRandomNumberBetween(300, 2000) * 1024;
        StringBuilder sb = new StringBuilder();

        while (size > 0) {
            sb.append('a');
            size--;
        }
        return sb.toString();
    }
}
