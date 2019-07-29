import java.util.Deque;

public class Producer implements Runnable {
    //You just need to keep in mind that if the object reference changes, the same section of code may be run in parallel.
    private Deque<Integer> taskQueue;
    private final int MAX_QUEUE_CAPACITY = 5;

    private int taskCounter = 0;

    public Producer(Deque<Integer> sharedQueue) {
        this.taskQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (taskQueue) {
                try {
                    while (taskQueue.size() == MAX_QUEUE_CAPACITY) {
                        System.out.println("Producer waiting!");
                        taskQueue.wait();
                    }
                    //used for better understanding the concept
                    Thread.sleep(1000);
                    int taskNumber = taskCounter++;
                    taskQueue.push(taskNumber);
                    System.out.println(String.format("Added task #%s to queue", taskNumber));

                    taskQueue.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
