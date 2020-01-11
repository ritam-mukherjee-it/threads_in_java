package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreProgram2 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Semaphore semaphore = new Semaphore(4);
        for (int i = 0; i < 10; i++) {
            executorService.submit(
                    new Thread(() -> {
                        if(semaphore.tryAcquire()){
                            try {
                                semaphore.acquire();
                                System.out.println(Thread.currentThread().getName() + "is now processing");
                                Thread.sleep(15000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                semaphore.release();
                            }
                        }else{
                            System.out.println(Thread.currentThread().getName() + "is now waiting");
                        }

                    },"thread_"+i));
        }


        executorService.shutdown();
    }
}
