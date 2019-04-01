package concepts.threads.thread_local;

/**
 * Created by PouRit on 08-07-2017.
 */
interface iCustomerThread {

    public void initialCustomerThread(String name, int cutomerId, Runnable runnable);
}

public class ThreadLocalLambdaProgram_1 {
    public static void main(String[] args) {

        iCustomerThread iCustomerThread = (name, cutomerId, runnable) -> {

            runnable = () -> {
                ThreadLocal cId = new ThreadLocal();
                cId.set(cutomerId);

                Thread.currentThread().setName(name);
                System.out.println("the Thread " + Thread.currentThread().getName() +
                        " is executing having Customer is:" + cId.get());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            new Thread(runnable).start();
        };
        for (int i = 1; i <= 5; i++) {

            iCustomerThread.initialCustomerThread("nam_" + i, i, new Thread());
        }
    }
}
