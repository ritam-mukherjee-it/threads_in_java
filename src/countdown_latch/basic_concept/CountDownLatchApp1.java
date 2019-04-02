package countdown_latch.basic_concept;

import countdown_latch.ThreadColor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
    private CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch) {

        this.countDownLatch = countDownLatch;
    //        Thread.currentThread().setName(threadname);
    }

    @Override
    public void run() {
        String STRING_COLOR= ThreadColor.getThreadColor.apply(Thread.currentThread().getName());


        System.out.println(STRING_COLOR+ Thread.currentThread().getName()+" has started,\'Latch count\':"
                        + countDownLatch.getCount());
        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
        System.out.println(STRING_COLOR+Thread.currentThread().getName() +" has reduced count,'Latch count':" + countDownLatch.getCount());
    }
}
public class CountDownLatchApp1 {
    public static void main(String[] args) {
        try{
        CountDownLatch latch=new CountDownLatch(5);
        Thread green_thread = new Thread(new Processor(latch), "green");
        Thread red_thread = new Thread(new Processor(latch), "red");
        Thread cyan_thread = new Thread(new Processor(latch), "cyan");
        Thread purple_thread = new Thread(new Processor(latch), "purple");
        Thread blue_thread = new Thread(new Processor(latch), "blue");

            green_thread.start();
            Thread.sleep(200);
            red_thread.start();
            Thread.sleep(200);
            cyan_thread.start();
            Thread.sleep(200);
            purple_thread.start();
            Thread.sleep(200);
            blue_thread.start();
            Thread.sleep(200);
            latch.await(); /* waiting to make count down latch 0*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ThreadColor.ANSI_RESET+"<><><><><><><><>complete<><><><><><><><>");

    }
}
