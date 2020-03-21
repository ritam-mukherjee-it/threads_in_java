package interthread_communication.yield;

import interthread_communication.ThreadColor;

public class ThreadYieldProgram {
    public static void main(String[] args) {

        Thread yield=new Thread(() -> {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            for (int i = 0; i < 5; i++) {
                System.out.println(ThreadColor.ANSI_GREEN+Thread.currentThread().getName()+" is executing, Count:"+i);
                Thread.yield();
            }

        },"yield Thread");
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        yield.start();
        for (int i = 0; i < 5; i++) {
            System.out.println(ThreadColor.ANSI_RED+Thread.currentThread().getName()+" is executing, Count:"+i);
        }
    }
}
