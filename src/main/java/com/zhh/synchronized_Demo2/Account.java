package com.zhh.synchronized_Demo2;

/**
 * Demo3
 * 指定给某个对象加锁
 */

/**
 * 银行账户类
 */
public class Account {
    String name;
    float amount;

    public Account(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }
    //存钱
    public void deposit(float amt){
        amount+=amt;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //取钱
    public void whihdraw(float amt){
        amount-=amt;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public float getBalance(){
        return amount;
    }

    
}
/**
 * 账户操作*/
 class AccountOperator implements Runnable{
     private Account account;

    public AccountOperator(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        synchronized (account){
            account.deposit(500);
            account.whihdraw(500);
            System.out.println(Thread.currentThread().getName()+":"+account.getBalance());
        }
    }
    public static void main(String[] args) {
        Account account=new Account("zhang san",1000.0f );
        AccountOperator accountOperator=new AccountOperator(account);
        final  int THREAD_NUM=5;
        Thread threads[]=new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i]=new Thread(accountOperator, "Thread"+i);
            threads[i].start();
        }
    }
}