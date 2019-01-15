package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This program demostrate the thread comes in waiting queue same order they are executing.
 */
public class LockProgram5 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);

        Runnable runnable = () -> {
            System.out.println(ThreadColor.getThreadColor.apply(Thread.currentThread().getName())
                    + Thread.currentThread().getName() +
                    ".. got lock performing safe operation");
            lock.lock();
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(ThreadColor.getThreadColor.apply(Thread.currentThread().getName())
                            + Thread.currentThread().getName()
                            + " now executing :" + i);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            lock.unlock();

        };
        Thread green_thread = new Thread(runnable, "green");
        Thread red_thread = new Thread(runnable, "red");
        Thread cyan_thread = new Thread(runnable, "cyan");
        Thread purple_thread = new Thread(runnable, "purple");
        Thread blue_thread = new Thread(runnable, "blue");

        try {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
