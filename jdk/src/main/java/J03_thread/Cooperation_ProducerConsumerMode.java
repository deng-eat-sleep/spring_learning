package J03_thread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者/消费者模式
 * 1.协作的共享变量是队列
 * 2.生产者往队列放数据，若队列满了则wait
 * 3.消费者从队列取数据，若队列为空则wait
 */
public class Cooperation_ProducerConsumerMode {

    public static void main(String[] args) {
        BlockingQueueDemo queue = new BlockingQueueDemo(10);
        new Producer(queue).start();
        new Consumer(queue).start();
    }

    /**
     * 阻塞队列 演示
     * java提供了专门的阻塞队列
     * 接口类BlockingQueue/BlockDeque
     * 实现类
     */
    static class BlockingQueueDemo<E>{

        private Queue<E> queue;
        private int limit;

        public BlockingQueueDemo(int limit) {
            this.limit = limit;
            this.queue = new ArrayDeque<>(limit);
        }

        /**
         * synchronized避免竞态条件
         */
        public synchronized void put(E e) throws InterruptedException {
            //队列满时，wait
            while (queue.size() == limit){
                System.out.println("queue已满");
                wait();
            }
            queue.add(e);
            //数据加入队列后，唤醒线程
            notifyAll();
        }

        public synchronized E take() throws InterruptedException {
            //队列为空时，wait
            while (queue.isEmpty()){
                System.out.println("queue is null");
                wait();
            }

            E e = queue.poll();
            //取出队列后，唤醒线程
            notifyAll();

            return e;
        }
    }

    /**
     * 生产者
     */
    static class Producer extends Thread{
        BlockingQueueDemo<String> queue;
        public Producer(BlockingQueueDemo queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                while (true){
                    String task = String.valueOf(num);
                    queue.put(task);

                    System.out.println("producer task :" + task);
                    num++;
                    Thread.sleep((int)Math.random()*100);
                }
            }catch (Exception e){

            }

        }
    }

    /**
     * 消费者
     */
    static class Consumer extends Thread{
        BlockingQueueDemo<String> queue;

        public Consumer(BlockingQueueDemo queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true){
                    String task = queue.take();

                    System.out.println("Consumer task :" + task);
                    Thread.sleep((int)Math.random()*100);
                }
            }catch (Exception e){

            }

        }
    }
}
