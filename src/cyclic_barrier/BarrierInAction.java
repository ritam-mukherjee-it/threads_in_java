package cyclic_barrier;
/**
 * Source   :   PluralSight
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class BarrierInAction {
    public static void main(String[] args) {

        class ColourfulFriend implements Callable {

            private CyclicBarrier cyclicBarrier;
            private String nam;

            public ColourfulFriend(CyclicBarrier cyclicBarrier, String nam) {

                this.cyclicBarrier = cyclicBarrier;
                this.nam = nam;
            }

            @Override
            public String call() throws Exception {

                Random random = new Random();
                Thread.sleep(random.nextInt(20) * 100 + 100);
                System.out.println(ThreadColor.getThreadColor.apply(nam)
                        +nam+"_friend"
                        + ":I just arrived,waiting for others...");
                cyclicBarrier.await();

                System.out.println(ThreadColor.getThreadColor.apply(nam)
                        +nam+"_friend"
                        + ":let's go for a cinema");
                return "ok";
            }
        }


        ExecutorService service = Executors.newFixedThreadPool(4);
        /*In cyclicBariier if there is a count of 4, at least 4 thread needed in block to release barrier
         * In this program: if the count of Thread we reduce less than 4 and if we don't mention waiting time
          * barrier will not open,
         *
         * */
        CyclicBarrier barrier = new CyclicBarrier(4, () ->
                System.out.println(ThreadColor.ANSI_RESET + "barrier Opening..."));
        List<Future<String>> futures = new ArrayList<>();

        try {
            ColourfulFriend green_friend = new ColourfulFriend(barrier, "green");
            ColourfulFriend red_friend = new ColourfulFriend(barrier, "red");
            ColourfulFriend cyan_friend = new ColourfulFriend(barrier, "cyan");
            ColourfulFriend blue_friend = new ColourfulFriend(barrier, "blue");
            futures.add(service.submit(green_friend));
            futures.add(service.submit(red_friend));
            futures.add(service.submit(cyan_friend));
            futures.add(service.submit(blue_friend));


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
