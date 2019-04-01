package concepts.threads.thread_local;

/**
 * Created by ritmukherjee on 9/20/2016.
 */
class CustomerThread implements Runnable {

    private static int customeId = 0;

    public CustomerThread(String s) {
    }

    ThreadLocal tl = new ThreadLocal() {
        @Override
        protected Integer initialValue() {
            return ++customeId;
        }
    };

    @Override
    public void run() {
        System.out.println("Running Thread is   :" + Thread.currentThread().getName() + " & Thread Local CustomerId :" + tl.get());
    }
}

public class ThreadLocalProgram_2 {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new CustomerThread("Thread_" + i));
            thread.start();
        }

    }

}
