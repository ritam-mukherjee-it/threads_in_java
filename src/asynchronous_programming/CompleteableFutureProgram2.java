package asynchronous_programming;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteableFutureProgram2 {
    public static void main(String[] args) {

        ExecutorService service=Executors.newFixedThreadPool(5);
        Runnable runnable_task1=() ->
                System.out.println(Thread.currentThread().getName() +"  Runnable_Task_1 has executed");
        CompletableFuture.runAsync(runnable_task1, service);

        Runnable runnable_task2=() ->
                System.out.println(Thread.currentThread().getName() +"  Runnable_Task_2 has executed");
        CompletableFuture.runAsync(runnable_task2);

        service.shutdown(); /*shut down the service*/
    }
}
