package com.atguigu.JMM;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {
    static AtomicReference atomicReference=new AtomicReference(100);
    static AtomicStampedReference atomicStampedReference=new AtomicStampedReference(100,1);

    public static void main(String[] args) {
        new Thread(()->{
            atomicReference.compareAndSet(100,100);

            //猫腻的过程
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
            System.out.println(atomicReference.compareAndSet(100, 101)+"\t"
            +atomicReference.get().toString());
        },"t2").start();

        //停个2秒，保证ABA彻底的解决
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("那就解决一下ABA的问题吧=======================================");
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t的版本号是："+atomicStampedReference.getStamp());
            //睡一会确保t4拿到相同版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(100,101,
                    stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t的版本号是："+atomicStampedReference.getStamp());

            atomicStampedReference.compareAndSet(101,100,
                    atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t的版本号是："+atomicStampedReference.getStamp());

        },"t3").start();

        new Thread(()->{
            int stamp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t的版本号是："+atomicStampedReference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,2019,
                    stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t的版本号是："+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+"\t当前值是："+atomicStampedReference.getReference());

        },"t4").start();
    }
}
