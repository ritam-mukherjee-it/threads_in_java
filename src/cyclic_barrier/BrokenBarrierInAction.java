package cyclic_barrier;

/**
 * Source   :   PluralSight
 * Purpose  : barrier wait for sometime, if then all sub-tasks execution not completed barrier breaks
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BrokenBarrierInAction {
    public static void main(String[] args) {

        class ColourfulFriend implements Callable {

            private CyclicBarrier cyclicBarrier;
            private String nam;

            public ColourfulFriend(CyclicBarrier cyclicBarrier, String nam) {

                this.cyclicBarrier = cyclicBarrier;
                this.nam = nam;
                Thread.currentThread().setName(nam);
            }

            @Override
            public String call() throws Exception {

                Random random = new Random();
                Thread.sleep(random.nextInt(20) * 100 + 100);
                System.out.println(ThreadColor.getThreadColor.apply(nam)
                        +nam+"_friend"
                        + ":I just arrived,waiting for others...");
                cyclicBarrier.await(10,TimeUnit.SECONDS);

                System.out.println(ThreadColor.getThreadColor.apply(nam)
                        +nam+"_friend"
                        + ":let's go for a cinema");
                return "ok";
            }
        }


        ExecutorService service= Executors.newFixedThreadPool(3);
        /*In cyclicBariier if there is a count of 4, at-least 4 thread needed in block to release barrier
        * In this program: if the count of Thread we reduce less than 4, barrier will not open, if we don't metion waiting time
        * */
        CyclicBarrier barrier=new CyclicBarrier(4,() -> System.out.println("barrier Opening..."));
        List<Future<String>> futures=new ArrayList<>();

        try {
            for (int i = 0; i <4 ; i++) {
                ColourfulFriend friend=new ColourfulFriend(barrier,ThreadColor.colors[i]);
                futures.add(service.submit(friend));
            }

            futures.forEach(stringFuture -> {
                try {
                    stringFuture.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } finally {
            service.shutdown();
        }
    }
}
