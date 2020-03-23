package phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

class ProcessorForBarrier implements Runnable{
    private Phaser phaser;

    public ProcessorForBarrier(Phaser phaser) {

        this.phaser = phaser;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        phaser.arrive();
    }
}
public class PhaserAsCyclicBarrier {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(4);
        Phaser phaser=new Phaser(3);

        service.submit(new ProcessorForLatch(phaser));
        service.submit(new ProcessorForLatch(phaser));
        service.submit(new ProcessorForLatch(phaser));

        phaser.awaitAdvance(1); /*similar to barrier await()*/

        System.out.println("Dependent services are initialized");
        service.shutdown();
    }
}
