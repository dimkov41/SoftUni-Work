import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    public static void main(String[] args) {
        Deque<Integer> sharedDeque = new LinkedBlockingDeque<>();
        Thread producer = new Thread(new Producer(sharedDeque));
        Thread consumer = new Thread(new Consumer(sharedDeque));
        producer.start();
        consumer.start();
    }
}
