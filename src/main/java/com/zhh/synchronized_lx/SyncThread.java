package com.zhh.synchronized_lx;

/**
 * synchronized修饰代码块，被修饰的代码块称为同步代码块，其作用范围是大括号{}括起来的代码，
 * 作用的对象是调用这个代码块的对象；
 */
/*修饰一个代码块*/
    //1.一个线程访问一个对象的synchronized(this)同步代码块时，其他试图访问该对象的线程将被阻塞
public class SyncThread  implements Runnable{
    private static int count;
    public SyncThread(){
        count =0;
    }

    @Override
    public void run() {
        synchronized (this){
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+":"+(count++));
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

    }
    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        SyncThread syncThread =new SyncThread();
        Thread thread1 =new Thread(syncThread,"synchronizedDemo1");
        Thread thread2 =new Thread(syncThread,"synchronizedDemo2");
        thread1.start();
        thread2.start();
        /**
         * 当两个并发线程（Thread1，Thread2）访问同一个对象（syncThread）
         * 中的synchronized代码块时，在同一时刻只能有一个线程得到执行，
         * 另一个线程受阻，必须等待当前线程执行完这个代码块之后才能执行该代码块。
         * Thread1和Thread2是互斥的，因为在执行synchronized代码块时会锁定当前的对象，
         * 只有执行完该代码块才能释放该对象锁，下一个线程才能执行并锁定该对象
         */

        /*不是说一个线程执行synchronized代码块时其它的线程受阻塞吗？
        为什么上面的例子中thread1和thread2同时在执行。
        这是因为synchronized只锁定对象，每个对象只有一个锁（lock）与之相关联，
        而上面的代码等同于下面这段代码：*/
        /*SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();*/
        /**
         * 这时创建了两个SyncThread的对象syncThread1和syncThread2，
         * 线程thread1执行的是syncThread1对象中的synchronized代码(run)，
         * 而线程thread2执行的是syncThread2对象中的synchronized代码(run)；
         * 我们知道synchronized锁定的是对象，这时会有两把锁分别锁定syncThread1对象和syncThread2对象，
         * 而这两把锁是互不干扰的，不形成互斥，所以两个线程可以同时执行。
         */
    }

}
