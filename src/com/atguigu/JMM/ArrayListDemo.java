package com.atguigu.JMM;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类不安全的问题
 */
public class ArrayListDemo {

    public static void main(String[] args) {
      // List list = new ArrayList();
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();

        for (int i = 1; i <=3; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,3));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    /**
     * 1 故障现象：
     *     java.util.ConcurrentModificationException
     *
     * 2 导致原因：
     *
     * 3 解决方案：
     *      (答出来这三个才具备在并发环境下干活的资质，老员工才带你玩)
     *      3.1 用vector可以解决问题，但是Vector@since   JDK1.0
     *      ArrayList@since   1.2 都有了vector为何还用ArrayList呢？vector加锁数据一致性可以保证，但是并发性急剧下降
     *      不考虑线程安全用ArrayList
     *      3.2 不用vector用什么呢？
     *         Collections 工具类： Collections.synchronizedList()
     *      3.3 前面两个不许用
     *          JUC包里面新提供的一个类: CopyOnWriteArrayList写时复制技术
     *
     *
     * 4 优化建议：
     *
     */
}
