package com.atguigu.JMM;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者模式实现   阻塞队列版本
 */

public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(1);

        new Thread(()->{
            try {
                blockingQueue.put(1);
                System.out.println(Thread.currentThread().getName()+"\t 插入了1");
                blockingQueue.put(2);
                System.out.println(Thread.currentThread().getName()+"\t 插入了2");
                blockingQueue.put(3);
                System.out.println(Thread.currentThread().getName()+"\t 插入了3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t 取走了"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t 取走了"+blockingQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t 取走了"+blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

    }
}
