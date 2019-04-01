package concepts.threads.thread_local;

/**
 * Created by ritmukherjee on 9/20/2016.
 */

class ParentThread implements Runnable {

    static ThreadLocal tl = new ThreadLocal();

    @Override
    public void run() {
        tl.set("AA");
        System.out.println("Current Thread is   :   " + Thread.currentThread().getName() + "" +
                " and its thread local variable tl is  :   " + ParentThread.tl.get());
        Thread childThread = new Thread(new ChildThread(), "Child Thread");
        childThread.start();
    }
}

class ChildThread implements Runnable {
    @Override
    public void run() {
        System.out.println("Current Thread is   :   " + Thread.currentThread().getName() + "" +
                " and its thread local variable tl is  :   " + ParentThread.tl.get());
    }
}

public class ThreadLocalPractice3 {
    public static void main(String[] args) {
        (new Thread(new ParentThread(), "Parent Thread")).start();
    }
}
