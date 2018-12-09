package asynchronous_programming;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureProgram1 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.runAsync(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " executing    :" + i);
            }
        });
        /*If we don't add any blocking code nothing will print because task assign at Fork-join pool */
        Thread.sleep(2000);
       /* If we add Thread.sleep() we will get output because we are halting main thread's execution.*/
    }
}
