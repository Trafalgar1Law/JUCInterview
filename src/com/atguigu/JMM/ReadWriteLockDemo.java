package com.atguigu.JMM;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 小总结：
 *  读读能共存
 *  读写不能共存
 *  写写不能共存
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <=5 ; i++) {
            final int tempI=i;
            new Thread(()->{
                myCache.write(tempI+"",tempI+"");
            },String.valueOf(tempI)).start();
        }

        for (int i = 1; i <=5 ; i++) {
            final int tempJ=i;
            new Thread(()->{
                myCache.read(tempJ+"");
            },String.valueOf(tempJ)).start();
        }
    }
}

class MyCache{
    private volatile Map map=new HashMap();
    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void write(String key,Object value){


        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 开始写");
            TimeUnit.SECONDS.sleep(2);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t 写完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }


    }

    public void read(String key){

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 开始读");
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读完成"+result);
        }finally {
            readWriteLock.readLock().unlock();
        }


    }

}
