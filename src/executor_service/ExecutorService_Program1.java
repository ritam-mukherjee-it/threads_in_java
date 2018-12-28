package executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorService_Program1 {
    public static void main(String[] args) {

        /*pool containing only one Thread*/
        ExecutorService pool1 = Executors.newSingleThreadExecutor();

        /*pool containing three live threads*/
        ExecutorService pool2 = Executors.newFixedThreadPool(3);

        /*thread count is dynamic, create on demand at runtime*/
        ExecutorService pool3 = Executors.newCachedThreadPool();

        /*As poo1 1 has only one thread so all task is executed by same thread*/
        for (int i = 0; i < 5; i++) {
            pool1.submit(new Thread(() ->
                    System.out.println(ThreadColor.ANSI_RED + Thread.currentThread().getName() + " id executing"),
                    "thread_" + i));
        }

        /*As poo1 2 has 3 threads so all task are distributed among 3 threads */
        for (int i = 0; i < 5; i++) {
            pool2.submit(new Thread(() ->
                    System.out.println(ThreadColor.ANSI_GREEN + Thread.currentThread().getName() + " id executing"),
                    "thread_" + i));
        }

        /*Five threads are created on demand*/
        for (int i = 0; i < 5; i++) {
            pool3.submit(new Thread(() ->
                    System.out.println(ThreadColor.ANSI_CYAN + Thread.currentThread().getName() + " id executing"),
                    "thread_" + i));
        }

        /*register with shutdown() hook*/
        pool1.shutdown();
        pool2.shutdown();
        pool3.shutdown();
    }
}
