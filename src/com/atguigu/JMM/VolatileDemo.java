package com.atguigu.JMM;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static void main(String[] args) {

        //seeOkByVolatile();
        MyData myData = new MyData();
        for (int i = 0; i <20 ; i++) {
            new Thread(()->{
                for (int j = 0; j <1000 ; j++) {
                    myData.addPlusPlus();
                    myData.addAutomic();
                }
            },String.valueOf(i)).start();
        }

        while (Thread.activeCount()>2){
            //除了主线程和gc线程外，还有的话就要等其他20个线程执行完毕
            Thread.yield();
        }

        System.out.println(myData.number);
        System.out.println(myData.atomicInteger);
    }
    //可见性:volatile可以保证可见性，及时通知其他线程的主物理内存的值已被修改
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName()+"\t update number");
        },"A").start();

        while (myData.number==0){

        }

        System.out.println(Thread.currentThread().getName()+"\t run");
    }
}

class MyData{
    volatile int number=0;

    public void addTo60(){
        this.number=60;
    }
    //why?不能保证线程安全呢？
    //怎么解决不保证原子性？
    //  第一种：加锁，但是杀鸡用牛刀了
    //
    public void addPlusPlus(){
        number++;
    }

    AtomicInteger atomicInteger= new AtomicInteger();
    /**
     * Creates a new AtomicInteger with initial value {@code 0}.
     */
    //由此可是，此时的atomicInteger相当于number
    public void addAutomic(){
        atomicInteger.getAndIncrement();
    }
}
