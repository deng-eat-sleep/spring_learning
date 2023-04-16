package J03_thread;

import org.junit.Test;


/**
 * 集合点
 */
public class Cooperation_AssemblePointDemo {


    @Test
    public void test() throws InterruptedException {
        int num = 10;
        AssemblePoint point = new AssemblePoint(num);

        for(int i = 0 ;i < num; i++){
            SubTread subTread = new SubTread(point);
            subTread.start();
        }

//        Thread.sleep(1000);
    }


    /**
     * 子线程
     */
    class SubTread extends Thread{
       private AssemblePoint point;

        public SubTread(AssemblePoint point) {
            this.point = point;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((int)Math.random()*100);

                //到达终点
                point.await();
                System.out.println(Thread.currentThread().getName() + "arrived");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 集合点
     */
    class AssemblePoint {
        private int n;

        public AssemblePoint(int n) {
            this.n = n;
        }

        /**
         * 到处终点
         */
        public synchronized void await() throws InterruptedException {
            if (n > 0) {
                n--;
                System.out.println(n);

                //全部到达，唤醒线程
                if(n == 0){
                    notifyAll();
                }else{
                    while (n!=0){
                        wait();
                    }
                }
            }
        }
    }
}
