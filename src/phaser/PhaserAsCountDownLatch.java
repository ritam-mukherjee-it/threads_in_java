package phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * program needs modification
 */
class ProcessorForLatch implements Runnable{
    private Phaser phaser;

    public ProcessorForLatch(Phaser phaser) {

        this.phaser = phaser;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arriveAndAwaitAdvance();
    }
}
public class PhaserAsCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service=Executors.newFixedThreadPool(4);
        Phaser phaser=new Phaser(1);

        service.submit(new ProcessorForLatch(phaser));
        service.submit(new ProcessorForLatch(phaser));
        service.submit(new ProcessorForLatch(phaser));

        phaser.awaitAdvance(1); /*similar to latch countDown()*/

        System.out.println("Dependent services are initialized");
        service.shutdown();
    }
}
