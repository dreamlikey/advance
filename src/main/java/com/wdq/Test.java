package com.wdq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: wudq
 * @Date: 2018/11/7
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
//        float f = 3.3;
//        double d = 3.33;
//        int i = 4L;
//        long l = 434;
//        int in = 1/3.3;
        count();
    }

    public static void sort() {
        int[] nums = {5,8,787,0,0,12,334,8772};
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            list.add(new Integer(num));
        }
        Collections.sort(list);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

    public static void count() {
        int count = 0;
        System.out.println(count++);
        for (int i = 0; i<10; ++i) {
//            System.out.println("++i:"+i);
//            System.out.println("count++:"+ count++);
        }
        System.out.println(count);
    }
}

class Car {}
class Dervied extends Base {
    private String name = "dervied";
    public Dervied() {
//        tellName();
//        printName();
    }
    public void tellName() {
        super.tellName();
        System.out.println("Dervied tell name: " + name);
    }

    public void printName() {
        System.out.println("Dervied print name: " + name);
    }
    
//    public static void main(String[] args){
//        new Dervied();
//    }
}

class Eervied extends Dervied {
    private String name = "Eervied";

    public static void main(String[] args){
        Eervied eervied = new Eervied();
//        ((Base)eervied).printName();
        eervied.tellName();
        System.out.println("=====================");
        Base base = new Base();
        base.tellName();

    }
}

class Base {
    private String name = "base";
    public Base() {
//        tellName();
//        printName();
    }
    public void tellName() {
        System.out.println("Base tell name: " + name);
        printName();//调用的是子类的printName()
    }

    public void printName() {
        System.out.println("Base print name: " + name);
    }
}