package J03_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 此线程可以返回值
 */
public class Thread_Callable {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for(int i = 0; i < 10; i++){
                    sum += i;
                }
                return sum;
            }
        };

        Callable<Integer> callable1 = ()-> {
            int sum = 0;
            for(int i = 0; i < 10; i++){
                System.out.println(sum);
                sum = sum + i;
            }
            return sum;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}
