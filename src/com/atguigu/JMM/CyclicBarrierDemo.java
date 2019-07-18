package com.atguigu.JMM;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//集齐龙珠zhaohuanshenlong
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐了七颗龙珠，召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            final int tempI=i;
            new Thread(()->{
                System.out.println("集齐了第"+tempI+"颗龙珠");
                try {
                    //第一颗龙珠收集到了，能不能召唤？不能，要等着
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
