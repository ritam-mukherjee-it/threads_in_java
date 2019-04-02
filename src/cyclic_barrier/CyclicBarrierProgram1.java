package cyclic_barrier;

import countdown_latch.ThreadColor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierProgram1 {


    public static void main(String[] args) {
         /*If the CyclicBarrier count is 3 then thread will not stop at barrier
                otherwise if the barrier count is more than barrier will stop the execution*/
        CyclicBarrier barrier=new CyclicBarrier(5);

        Runnable runnable=() -> {
            String STRING_COLOR= ThreadColor.getThreadColor.apply(Thread.currentThread().getName());

            System.out.println(STRING_COLOR+Thread.currentThread().getName() + " has started,\'Latch count\':" + barrier.getParties());
            try {
                Thread.sleep(10000);
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }


            System.out.println(STRING_COLOR+Thread.currentThread().getName() + " has reduced count,'Latch count':" + barrier.getParties());
        };

        new Thread(runnable, "green").start();
        new Thread(runnable, "red").start();
        new Thread(runnable, "yellow").start();
        new Thread(runnable, "blue").start();
        new Thread(runnable, "cyan").start();


        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ThreadColor.ANSI_RESET+"<><><><><><><><><>complete<><><><><><><><><>");
    }
}
