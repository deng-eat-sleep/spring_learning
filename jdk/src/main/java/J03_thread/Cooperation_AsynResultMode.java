package J03_thread;

import java.util.concurrent.Callable;

/**
 * 异步结果
 */
public class Cooperation_AsynResultMode {

    public static void main(String[] args) {
        // 子任务
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int mill = (int) (Math.random() * 1000);
                Thread.sleep(mill);
                return mill;
            }
        };

        //主线程调用子线程
        MyExecutor myExecutor = new MyExecutor();

        // 异步调用
        Future<Integer> future = myExecutor.execute(callable);

        try {
            //获取异步结果
            int mill = future.get();
            System.out.println("返回结果为：" + mill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行器
     * 主线程调用子线程
     */
    static class MyExecutor {

        public <V> Future<V> execute(final Callable<V> task) {
            final Object lock = new Object();//对象锁

            //执行callable线程
            final ExecuteThread<V> executeThread = new ExecuteThread<>(task, lock);
            executeThread.start();

            Future<V> future = new Future<V>() {
                @Override
                public V get() throws Exception {
                    synchronized (lock) {
                        //线程未执行完成
                        while (!executeThread.isDone()) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                    if (executeThread.getException() != null) {
                        throw executeThread.getException();
                    }
                    return executeThread.getResult();
                }
            };
            return future;
        }
    }

    /**
     * 执行线程
     * callable需求thread启动运行
     */
    static class ExecuteThread<V> extends Thread {
        private V result = null;
        private Exception exception = null;
        private boolean done = false;
        private Callable<V> task;
        private Object lock;

        public ExecuteThread(Callable<V> task, Object lock) {
            this.task = task;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                result = task.call();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                synchronized (lock) {
                    //运行结束后，done为true
                    done = true;
                    lock.notifyAll();
                }
            }
        }

        public V getResult() {
            return result;
        }

        public boolean isDone() {
            return done;
        }

        public Exception getException() {
            return exception;
        }
    }

    static interface Future<V>{
       V get() throws Exception;
    }

}
