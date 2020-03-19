package thread_interruption;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class ThreadInterruptionRunner {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable=() -> {
            try {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted");
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("Child Thread executing");

                    // Here current threads goes to sleeping state
                    // Another thread gets the chance to execute
                    Thread.sleep(1000);
                }

            }

            catch (InterruptedException e) {
                System.out.println("InterruptedException occur");
            }
        };
        Thread t=new Thread(runnable);


        t.start();
      //  Thread.currentThread().sleep(1000);

       t.interrupt();
       Thread.sleep(500);
        System.out.println("Main thread execution completes");

    }
}
