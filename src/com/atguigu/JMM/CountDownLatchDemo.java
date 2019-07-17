package com.atguigu.JMM;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //CloseDoorDemo();
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6 ; i++) {
            final int tempI=i;
            new Thread(()->{
                System.out.println(Country.getCountryName(tempI).getName()+"\t 国灭了");
                countDownLatch.countDown();
            },String.valueOf(tempI)).start();
        }
        countDownLatch.await();
        System.out.println("秦统一了六国");
    }

    private static void CloseDoorDemo() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        //班长关门
        for (int i = 1; i <=6; i++) {
            final int tempI=i;

            new Thread(()->{
                System.out.println(tempI+"\t 离开了教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长锁门了");
    }
}

enum Country{
    ONE(1,"齐国"),
    TWO(2,"楚国"),
    THREE(3,"燕国"),
    FOUR(4,"赵国"),
    FIVE(5,"魏国"),
    SIX(6,"韩国");

    private  String name;

    private Integer number;

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    private Country(Integer number, String name){
        this.name=name;
        this.number=number;
    }

    public static Country getCountryName(Integer number){
        Country[] values = Country.values();
        for (Country value : values) {
            if(number==value.getNumber()){
                return value;
            }
            String name = value.getName();
        }
        return null;
    }


}
