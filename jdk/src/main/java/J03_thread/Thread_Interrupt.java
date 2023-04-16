package J03_thread;

import org.junit.Test;

/**
 * 中断
 * 1.中断标志位默认false
 * 2.interrupt()方法只能设置中断标志位，不能真正中断线程，只是一种协作机制
 * 3.线程状态，调用interrupt()方法
 * Runnable状态：只设置标志位
 * Waiting/Timed_Waiting: 线程会抛出InterruptedException，并清空标志位
 * Blocked: 只设置标志位，不会使它从锁池中出来，仍然是Blocked状态
 * New/Terminated: 不做任何操作
 */
public class Thread_Interrupt {

    @Test
    public void test() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    //
                    System.out.println("start:" + isInterrupted());

                    //设置中断标志位
                    Thread.currentThread().interrupt();

                    System.out.println("start:" + isInterrupted());

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //interrupt异常会清空中断标志位，中断标志位重新变成false
                    System.out.println(isInterrupted());

//                    //重新设置中断标志位
//                    Thread.currentThread().interrupt();
//                    System.out.println(isInterrupted());
                }
            }
        };

        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程在wait状态下，调用interrupt(),会抛出异常，并清空中断标志位
        thread.interrupt();


        System.out.println("1");
    }
}
