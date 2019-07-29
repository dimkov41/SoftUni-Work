import java.util.Deque;

public class Consumer implements Runnable {
    //You just need to keep in mind that if the object reference changes, the same section of code may be run in parallel
    private Deque<Integer> taskQueue;

    public Consumer(Deque<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (taskQueue) {
                try {
                    while (taskQueue.isEmpty()) {
                        System.out.println("Consumer waiting!");
                        taskQueue.wait();
                    }
                    //used for better understanding the concept
                    Thread.sleep(1000);
                    int task = taskQueue.poll();
                    System.out.println(String.format("Getted task #%s from queue", task));

                    taskQueue.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
