package com.atguigu.JMM;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

//阻塞队列
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(3);

       /* //抛出异常
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
       // System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        System.out.println(blockingQueue.remove());*/


        /*System.out.println(blockingQueue.offer("1"));
        System.out.println(blockingQueue.offer("2"));
        System.out.println(blockingQueue.offer("3"));
        //System.out.println(blockingQueue.offer("4"));
        System.out.println(blockingQueue.peek());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/


       /* blockingQueue.put("a1");
        blockingQueue.put("a2");
        blockingQueue.put("a3");
        //blockingQueue.put("a4");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

        System.out.println(blockingQueue.offer("b1", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b2", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b3", 2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b4", 2, TimeUnit.SECONDS));




    }
}
