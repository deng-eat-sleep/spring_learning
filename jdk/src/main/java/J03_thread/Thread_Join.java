package J03_thread;


import org.junit.Test;

/**
 * join()
 * 调用此方法的线程需先执行完成，其他线程才能继续执行
 */
public class Thread_Join {

    @Test
    public void test01() throws Exception {
        Thread a = new Thread(() ->{
            for(int i = 0 ;i<100; i++){
                System.out.println("a：" + i);
            }
        });

        Thread b = new Thread(() -> {
            for(int i = 0 ;i < 100; i++){
                System.out.println("b：" + i);
            }
        });

        a.start();
        b.start();

//        a.join(); //a线程执行完成 b线程才能执行
//        b.join(); //a线程执行完成 main线程才能执行

        System.out.println("main");
    }
}
