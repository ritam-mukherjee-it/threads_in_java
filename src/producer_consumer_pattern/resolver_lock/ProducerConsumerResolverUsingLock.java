package producer_consumer_pattern.resolver_lock;

import producer_consumer_pattern.ThreadColor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProducerConsumerResolverUsingLock {
    static Lock lock = new ReentrantLock();
    static Condition isFull = lock.newCondition();
    static Condition isEmpty = lock.newCondition();
    private static int[] buffer;
    private static int count;

    static class Producer {
        void produce() {

            try {
                lock.lock();
                if (isFull(buffer))
                    isFull.await();
                buffer[count++] = 1;
                isEmpty.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Consumer {
        void consume() {
            try {
                lock.lock();
                if (isEmpty(buffer))
                    isEmpty.await();
                buffer[--count] = 0;
                isFull.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isEmpty.signal();
            }
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

        ProducerConsumerResolverUsingLock.Producer producer = new ProducerConsumerResolverUsingLock.Producer();
        ProducerConsumerResolverUsingLock.Consumer consumer = new ProducerConsumerResolverUsingLock.Consumer();
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
