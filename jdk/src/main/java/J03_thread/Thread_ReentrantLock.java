package J03_thread;

import java.util.LinkedList;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class Thread_ReentrantLock {

    public  void test(){
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();


        LockSupport.park();
    }
}
