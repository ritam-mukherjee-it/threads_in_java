package asynchronous_programming;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class CompletableFutureProgram4 {
    public static void main(String[] args) {

        Supplier<String> task = () -> {
            try {
                Thread.sleep(1000); /*waiting for 1000 milis*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return Thread.currentThread().getName() + "is executing";
        };
        CompletableFuture<String> completableFuture1 = CompletableFuture
                .supplyAsync(task);
        CompletableFuture<String> completableFuture2 = CompletableFuture
                .supplyAsync(task);

        /*forcefully complete the task for completableFuture1*/
        completableFuture1.complete("too long waiting period");
        System.out.println(completableFuture1.join());
        /*allowing to colplete the task for completableFuture2*/
        System.out.println(completableFuture2.join());
        /*reverse order will not work because completableFuture2 will get time*/
    }
}
