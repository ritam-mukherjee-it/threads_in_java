package countdown_latch.lambda_approach;




import countdown_latch.ThreadColor;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchLambdaApproach1 {
    public static void main(String[] args) {

        CountDownLatch countDownLatch=new CountDownLatch(5);

        Runnable runnable=() -> {
            String STRING_COLOR= ThreadColor.getThreadColor.apply(Thread.currentThread().getName());

            System.out.println(STRING_COLOR+Thread.currentThread().getName() + " has started,\'Latch count\':" + countDownLatch.getCount());
            try {
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            countDownLatch.countDown();
            System.out.println(STRING_COLOR+Thread.currentThread().getName() + " has reduced count,'Latch count':" + countDownLatch.getCount());
        };

        new Thread(runnable, "green").start();
        new Thread(runnable, "red").start();
        new Thread(runnable, "yellow").start();
        new Thread(runnable, "blue").start();
        new Thread(runnable, "cyan").start();

        try {
            countDownLatch.await(); /* waiting to make count down latch 0*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ThreadColor.ANSI_RESET+"<><><><><><><><><>complete<><><><><><><><><>");

    }
}
