package locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ritmukherjee on 11/27/2016.
 */
public class LockProgram1 {
    public static void main(String[] args) {

        ReentrantLock l = new ReentrantLock(); //fairness policy not given

       /* no l is hold right now*/
        System.out.println("Is any lock hold now?   " + l.isLocked());

        /* default fairness policy is "false"*/
        System.out.println("Is fairness policy set? " + l.isFair());

        /*if any lock is not acquire default hold count is 0*/
        System.out.println("the hold count is   :   " + l.getHoldCount());
        System.out.println("Is current thread is hold lock? " + l.isHeldByCurrentThread());
        System.out.println("No of thread waiting for lock   :   " + l.getQueueLength());

        l.lock();   /*current thread get one lock*/
        l.lock();   /*current thread get second lock*/

        System.out.println("the hold count is   :   " + l.getHoldCount());

        l.unlock(); /* one lock has released*/

        System.out.println("the hold count is   :   " + l.getHoldCount());
        System.out.println("Is any lock hold now?   " + l.isLocked());
        System.out.println("Is current thread is hold lock? " + l.isHeldByCurrentThread());

        l.unlock(); /* another lock has released*/

        System.out.println("the hold count is   :   " + l.getHoldCount());
        System.out.println("Is any lock hold now?   " + l.isLocked());
    }
}
