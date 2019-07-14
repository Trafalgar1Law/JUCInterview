package com.atguigu.JMM;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
       /* new Thread(()->{
            phone.sendSms();
        },"t1").start();

        new Thread(()->{
            phone.sendSms();
        },"t2").start();*/

        Thread thread = new Thread(phone,"A");
        thread.start();

        Thread thread1 = new Thread(phone,"B");
        thread1.start();
    }
}

class Phone implements Runnable{
    ReentrantLock lock = new ReentrantLock();
    public synchronized void sendSms(){
        System.out.println(Thread.currentThread().getId()+"\t sendSms()");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getId()+"\t sendEmail()");
    }


    @Override
    public void run() {
        get();
    }

    public void get(){
        lock.lock();
        lock.lock();
        try {

            System.out.println(Thread.currentThread().getName()+"\t get()");
            set();
        }finally{
           // lock.unlock();
            lock.unlock();
        }

    }

    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t set()");

        }finally {
            lock.unlock();
        }
    }
}
