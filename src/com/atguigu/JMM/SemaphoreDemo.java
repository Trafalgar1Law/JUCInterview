package com.atguigu.JMM;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//信号灯
public class SemaphoreDemo {
    public static void main(String[] args) {
        //假设有3个车位
        Semaphore semaphore = new Semaphore(3);
        //现在是6辆车抢车位
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到了车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t     离开了车位");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
