package io.sudheer.practice.simple.sorting;

import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class ArrayListTS {
    public static void main(String[] args) {
        HashSet<Integer> hset = new HashSet<>();
        for (int i = 0; i <= 1000; i++) {
            hset.add(i);
        }

        MyRunnable mr = new MyRunnable();
        mr.addElements(hset);

        Thread t1 = new Thread(mr,"t1");
        Thread t2 = new Thread(mr,"t2");
        Thread t3 = new Thread(mr,"t3");

        t1.start(); t2.start(); t3.start();

    }
}

class MyRunnable implements Runnable {

    List<Integer> ilist = Collections.synchronizedList(new ArrayList<>());

    public void addElements(HashSet<Integer> hset) {
        ilist.addAll(hset);
    }

    @Override
    public void run(){
        Collections.sort(ilist);
        if (CollectionUtils.isNotEmpty(ilist))
            System.out.println(Thread.currentThread().getName() + " = " + ilist.get(ilist.size() - 1));
        else
            System.out.println("List is empty");
    }
}
