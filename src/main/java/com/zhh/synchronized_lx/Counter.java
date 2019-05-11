package com.zhh.synchronized_lx;

/**
 *
 * 当一个线程访问对象的一个synchronized(this)同步代码块时，
 * 另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块。
 *  Demo2
 *
 */
//多个线程访问synchronized和非synchronized代码块
public class Counter implements Runnable{
    private int count;
    public Counter (){
        count=0;
    }

    public void countAdd() {
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
    //非synchronized代码块，对未count进行读写操作，所以可以不用synchronized
    public  void printCount(){
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println(Thread.currentThread().getName()+":"+(count++));
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        if (threadName.equals("A")) {
            countAdd();
        }else if (threadName.equals("B")){
            printCount();
        }

    }

    public static void main(String[] args) {
            Counter counter =new Counter();
            Thread thread1 = new Thread(counter,"A");
            Thread thread2 = new Thread(counter, "B");
            thread1.start();
            thread2.start();
    }
    /**
     * 上面代码中countAdd是一个synchronized的，printCount是非synchronized的。
     * 从上面的结果中可以看出一个线程访问一个对象的synchronized代码块时，
     * 别的线程可以访问该对象的非synchronized代码块而不受阻塞。
     */
}
