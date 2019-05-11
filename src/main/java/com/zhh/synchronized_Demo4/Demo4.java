package com.zhh.synchronized_Demo4;

public class Demo4 implements Runnable{

    private int count;
    public Demo4(){
        count=0;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName()+":"+(count++));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        Demo4 syncThread = new Demo4();
        Thread thread1 = new Thread(syncThread, "SyncThread1");
        Thread thread2 = new Thread(syncThread, "SyncThread2");
        thread1.start();
        thread2.start();
    }
}
