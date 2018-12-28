package callable_pattern;

import java.util.concurrent.*;

public class CallableGetvalueProgram {
    public static void main(String[] args) {

        Callable<String> task1 = () -> {
            Thread.sleep(1000);
            return Thread.currentThread().getName() + "has awaken up";
        };

        ExecutorService pool = Executors.newFixedThreadPool(4);

        /*Following piece of code does not use timeout in get*/
        for (int i = 0; i < 5; i++) {
            Future<String> future = pool.submit(task1);
            try {
                System.out.println(callable_pattern.ThreadColor.ANSI_CYAN + "Return value of task:" + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }
}
