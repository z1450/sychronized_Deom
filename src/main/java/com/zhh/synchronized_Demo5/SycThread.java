package com.zhh.synchronized_Demo5;

public class SycThread implements Runnable{
    private static int count;
    public SycThread(){
        count=0;
    }
    public synchronized static void method() {
        for (int i = 0; i < 5; i ++) {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + (count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void run() {
        method();
    }

    public static void main(String[] args) {
        SycThread sycThread1 =new SycThread();
        SycThread sycThread2 = new SycThread();
        Thread thread1 = new Thread(sycThread1, "SycThead1");
        Thread thread2 = new Thread(sycThread2, "SycThread2");
        thread1.start();
        thread2.start();
    }
    /**
     * syncThread1和syncThread2是SyncThread的两个对象，当是在thread1
     * 和thread2并发执行时却保持了线程同步。这是因为在线程run方法中调用了静态方法method，
     * 而静态方法属于类的，所以sycncThreas1和syncThread2相当于用了同一把锁
     *
     */
}
