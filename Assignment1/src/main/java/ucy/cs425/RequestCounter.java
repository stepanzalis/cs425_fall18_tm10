package main.java.ucy.cs425;

/**
 * @author Stepan Zalis Loukas Solea
 * created on 2018/10/18
 */
public class RequestCounter {

    private int numberOfRequests;
    private int counter;
    private int count;
    private int endCount;
    private int sum;

    public RequestCounter() {
        this.counter = 0;
        this.sum = 0;
        this.endCount = 0;
        this.count = 0;
        this.numberOfRequests = 0;
    }

    public void print() {
        System.out.println("################## OUTPUT ##################");
        System.out.println("The current sum is: " + sum);
        System.out.println("The current count is: " + count);
        System.out.println("The current throughput is: " + (double) sum / count);
        System.out.println("The number of requests: " + numberOfRequests);
    }

    public void incrementNumberOfRequests() {
        this.numberOfRequests += 1;
    }

    public void incrementCounter() {
        this.counter += 1;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public void setSum() {
        this.sum += counter;
    }

    public void incrementCount() {
        this.count += 1;
    }

    public void incrementEnd() {
        this.endCount += 1;
    }

    public int getEndCount() {
        return endCount;
    }

    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    public int getCounter() {
        return counter;
    }

    public int getCount() {
        return count;
    }

    public int getSum() {
        return sum;
    }
}