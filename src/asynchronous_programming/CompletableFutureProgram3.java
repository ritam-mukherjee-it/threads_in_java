package asynchronous_programming;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureProgram3 {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                   return Thread.currentThread().getName() + "is executing";
                });

        try {
            /**
             * getNow() : this method check whether value is available now?
             * it does not wait for completion of the task
             * if the value is present then immidiately return value
             */
            String result1 = completableFuture.getNow("not presnt");
            System.out.println(result1);
            /**
             * get() : this method wait until completion of the task
             * once the value is available then only present the value
             */
            String result2 = completableFuture.get();
            System.out.println(result2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
