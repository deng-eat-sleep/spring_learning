package J03_thread;

/**
 * 守护线程
 */
public class Thread_Demo {

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1);
                    System.out.println("2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);

        thread.start();
        Thread.sleep(1000);
        System.out.println("1111");

    }
}
