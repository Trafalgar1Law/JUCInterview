package com.atguigu.JMM;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 一个初始为零的变量，两个线程对其交互操作
 * 一个加1，一个减1，进行5轮
 *
 * 1 线程 操作 资源类
 * 2 判断 干活 通知
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) throws InterruptedException {
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareData.increment();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Prod").start();

        TimeUnit.SECONDS.sleep(3);

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}

class ShareData{
    private int number=0;
    private Lock lock=new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment(){

        lock.lock();
        try {
            while (number!=0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"生产了一块蛋糕");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
         while (number==0)  {
            condition.await();
         }
         number--;
            System.out.println(Thread.currentThread().getName()+"消费了一块蛋糕");
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
