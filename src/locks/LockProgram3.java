package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockProgram3 {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Runnable runnable = () -> {
            if (lock.tryLock()) {
                System.out.println(ThreadColor.getThreadColor.apply(Thread.currentThread().getName())
                        + Thread.currentThread().getName() +
                        ".. got lock performing safe operation");
                try {
                    lock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            } else {
                System.out.println(ThreadColor.getThreadColor.apply(Thread.currentThread().getName())
                        + Thread.currentThread().getName()
                        + ".. unable to get lock");
            }
        };
        Thread green_thread = new Thread(runnable, "green");
        Thread red_thread = new Thread(runnable, "red");
        Thread cyan_thread = new Thread(runnable, "cyan");
        Thread purple_thread = new Thread(runnable, "purple");
        Thread blue_thread = new Thread(runnable, "blue");
        try {
            green_thread.start();
            red_thread.start();
            Thread.sleep(200);
            cyan_thread.start();
            Thread.sleep(200);
            blue_thread.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
