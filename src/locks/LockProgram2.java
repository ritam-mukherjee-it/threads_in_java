package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @purope :   This program demonstrate how by log pattern we can achieve same functionality like synchronization
 */
public class LockProgram2 {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Runnable runnable = () -> {
            System.out.println(ThreadColor.getThreadColor.apply(Thread.currentThread().getName())
                    + Thread.currentThread().getName() +
                    ".. got lock performing safe operation");
            lock.lock();
            for (int i = 0; i < 4; i++) {
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

        green_thread.start();
        red_thread.start();
        cyan_thread.start();
        purple_thread.start();
        blue_thread.start();

    }
}
