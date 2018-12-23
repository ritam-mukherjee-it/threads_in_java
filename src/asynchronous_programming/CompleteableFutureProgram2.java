package asynchronous_programming;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompleteableFutureProgram2 {
    public static void main(String[] args) {

        ExecutorService service=Executors.newFixedThreadPool(5);


        Runnable runnable_task1=() ->
                System.out.println(Thread.currentThread().getName() +"  Runnable_Task_1 has executed");
        CompletableFuture.runAsync(runnable_task1, service);

        Runnable runnable_task2=() ->
                System.out.println(Thread.currentThread().getName() +"  Runnable_Task_2 has executed");
        CompletableFuture.runAsync(runnable_task2);

        System.out.println("-----------------------------------------");

        Supplier supply_task1=() -> Thread.currentThread().getName() +"  Supply_Task_1has executed";
        CompletableFuture<String > completableFuture1 = CompletableFuture.supplyAsync(supply_task1,service);
        String supply_message_1=completableFuture1.join();
        System.out.println(supply_message_1);


        Supplier supply_task2=() -> Thread.currentThread().getName() +"  Supply_Task_2 has executed";
        CompletableFuture<String > completableFuture2 = CompletableFuture.supplyAsync(supply_task2);
        String supply_message_2=completableFuture2.join();
        System.out.println(supply_message_2);

        service.shutdown(); /*shut down the service*/
    }
}
