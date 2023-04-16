package J03_thread;

public class Thread_Synchronized {


    public static void main(String[] args) {
        int num = 10;
        Counter counter = new Counter();
        for(int i = 0 ;i < 10 ;i++){
            Thread thread = new CounterThread(counter);
            thread.setName(String.valueOf(i));
            thread.start();
        }
    }


    public static class CounterThread extends Thread{

        Counter counter = new Counter();
        public CounterThread(Counter counter){
            this.counter = counter;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + counter);
        }
    }

    public static class Counter{
        private int count;

        public synchronized void incr(){
            count++;
        }

        public synchronized int getCount(){
            return count;
        }
    }

}
