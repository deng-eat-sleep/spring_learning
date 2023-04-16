package J03_thread;

/**
 *
 * 同时开始
 * 1.协作的共享变量为一个开始信号
 * 2.主线程发送开始信号
 * 3.子线程等待开始信号
 */
public class Cooperation_SameTimeStartMode {

    public static void main(String[] args)throws Exception {
        FireFlag fireFlag = new FireFlag();

        Thread[] recers = new Thread[10];
        for (int i = 0;i < 10; i++){
            recers[i] = new Racer(fireFlag);
            recers[i].start();
        }
        Thread.sleep(1000);

        //主线程发送开始信号
        fireFlag.fire();
    }

    /**
     * 开始信号
     */
    static class FireFlag{
        private volatile boolean fired = false;

        //等待信号
        public synchronized void waitForFire() throws Exception{
            //fired为false，wait
            while (!fired){
                System.out.println(Thread.currentThread().getName() + "信号未开始，wait");

                wait();
            }
        }

        public synchronized void fire(){
            fired = true;

            //信号开始，唤醒线程
            notifyAll();
        }
    }

    /**
     * 运动员
     */
    static class Racer extends Thread {

        FireFlag fireFlag;


        public Racer(FireFlag fireFlag) {
            this.fireFlag = fireFlag;
        }

        public void run() {
            try {
                //等待开始信号
                this.fireFlag.waitForFire();

                //信号开始后运行
                System.out.println("start run: " + Thread.currentThread().getName());
            } catch (Exception e) {

            }
        }
    }
}
