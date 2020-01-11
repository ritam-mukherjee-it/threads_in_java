package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@SuppressWarnings("WIP")
public class SemaphoreProgram1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 50; i++) {
            executorService.submit(
                    () -> {
                        try {
                            semaphore.acquire(); /* acquiring the lock*/
                            System.out.println(Thread.currentThread().getName() + "is now processing");
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            semaphore.release();  /*releasing the lock*/
                        }
                    });
        }

        executorService.shutdown();
    }
}
