package producer_consumer_pattern.problem;

import producer_consumer_pattern.ThreadColor;

/**
 * Source   :   PluralSight
 * Here the Expected output is '0' but out put can have three possibilities:
 *         1. count 0;
 *         2. count any value more than '0'
 *         3. deadlock situation due to improper 'race' condition.
 *  The problem we are getting due to race Condition.
 */
public class ProducerConsumerPoblem {
    private static int[] buffer;
    private static int count;

    static class Producer {
        void produce() {
            while (isFull(buffer)) {

            }
            buffer[count++] = 1;
        }
    }

    static class Consumer {

        void consume() {
            while (isEmpty(buffer)) {

            }
            buffer[--count] = 0;
        }
    }

    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        Runnable produceTask = () -> {
            for (int i = 0; i < 50; i++) {
                producer.produce();
            }
            System.out.println(ThreadColor.ANSI_GREEN + " Producing Done");
        };

        Runnable consumeTask = () -> {
            for (int i = 0; i < 50; i++) {
                consumer.consume();
            }
            System.out.println(ThreadColor.ANSI_RED + " Consuming Done");
        };

        Thread producer_thread = new Thread(produceTask);
        Thread consumer_thread = new Thread(consumeTask);

        consumer_thread.start();
        producer_thread.start();

        consumer_thread.join();
        producer_thread.join();

        System.out.println(ThreadColor.ANSI_CYAN + "Data in the buffer is   :" + count);
    }
}
