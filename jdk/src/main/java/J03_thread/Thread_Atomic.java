package J03_thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子变量
 */
public class Thread_Atomic {


    public static void main(String[] args) throws Exception{
        int num = 100;
        AtomicInteger count = new AtomicInteger(num);
        //共享变量
        Latch latch = new Latch(count);
        Worker[] workers = new Worker[num];
        for(int i= 0 ; i < num; i++){
            workers[i] = new Worker(latch);
            workers[i].start();
        }
        latch.await();

    }

    /**
     * 工作线程
     */
    static class Worker extends Thread{
        Latch latch;

        public Worker(Latch latch) {
            this.latch = latch;
        }

        public void run(){
            try{
                Thread.sleep((int)Math.random()*1000);

                //子线程运行结束，数量减1
                this.latch.countDown();

            }catch (Exception e){

            }
        }
    }

    /**
     * 共享变量
     * 线程统计
     */
    static class Latch{
        //线程数量
        private AtomicInteger count;

        public Latch(AtomicInteger count) {
            this.count = count;
        }

        /**
         * 有线程运行，主线程等待
         */
        public void await() throws Exception{
            while (count.get()>0){
                wait();
            }
        }

        /**
         * 子线程全部运行结束，唤醒主线程
         */
        public void countDown(){
            System.out.println("count:" + count.get());
            count.getAndDecrement();
            if(count.get()<=0){
                notifyAll();
            }
        }
    }

}
