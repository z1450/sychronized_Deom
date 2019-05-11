package com.zhh.sychronized_Demo6;

/**
 * 修饰一个类
 */
public class SyncThread implements Runnable{

    private static int count;
    public SyncThread(){
        count=0;
    }
    public static void method(){
        synchronized (SyncThread.class){
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println(Thread.currentThread().getName()+":"+(count++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Override
    public synchronized void run() {
        method();
    }

    public static void main(String[] args) {
        SyncThread syncThread1 = new SyncThread();
        SyncThread syncThread2 = new SyncThread();
        Thread thread1 = new Thread(syncThread1, "SyncThread1");
        Thread thread2 = new Thread(syncThread2, "SyncThread2");
        thread1.start();
        thread2.start();
    }
    /**
     * 其效果和【Demo5】是一样的，synchronized作用于一个类T时，
     * 是给这个类T加锁，T的所有对象用的是同一把锁。
     */
    /**
     * 总结
     *
     * 1）无论sychronized关键字加在方法上面还是对象上面，
     * 如果它的作用对象是非静态的，则它取得的锁是对象
     * 如果sychronized作用的对象是一个静态方法或者一个类，
     * 则它取得的锁是对类，该类所以的对象同一把锁；
     * 2）每个对象只有一把锁（lock）与之相关联，
     * 谁拿到这个锁谁就可以运行它所控制的那段代码
     * 3）实现同步是要很大的系统开销作为代价的，甚至可以造成死锁，
     * 所以应该尽量避免无谓的同步控制
     *
     */
}
