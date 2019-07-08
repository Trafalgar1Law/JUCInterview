package com.atguigu.JMM;

public class SingletonDemo {
    //原先的单例模式
    //这里的volatile防止指令重排
    private static volatile SingletonDemo singletonDemo=null;

    private SingletonDemo(){

        System.out.println(Thread.currentThread().getName()+"调用了构造方法");
    }

    public static SingletonDemo getInstance(){
        if(singletonDemo==null){
            //我们真正需要控制的就是这么一行，加synchronized太重了
            //采用DCL（double check lock 双边检锁机制）
            synchronized (SingletonDemo.class){//锁上门
                if(singletonDemo==null){//不放心，再推推门
                    singletonDemo=new SingletonDemo();
                }
            }

        }
        //防止人还没到就去访问了
            return singletonDemo;
    }

    public static void main(String[] args) {
       // System.out.println(getInstance()==getInstance());
       // System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
        //试试多线程会出现什么问题？
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
/**
 * 双端检索机制不一定安全，因为有指令重排，加入volatile可以禁止指令重排
 *
 * 引用对象可能还没有完成初始化，new 分为三步执行
 *  //1 分配对象内存空间
 *  //2 初始化对象
 *  //3 设置指向分配的地址
 *
 */
