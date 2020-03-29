package com.springcloud.tmxk.juc.rain_012;

import com.springcloud.tmxk.desiginmodel.strategy.Rain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author Rain
 * @Date 2020/3/28 13:28
 * @Description
 * volatile
 * Volatile keyword is used to modify the value of a variable by different threads.
 * It is also make classes thread safe.
 * 1. You can use the volatile keyword with variable. Using volatile keyword with classes and methods is illegal.
 * 2. It guarantees that value of the volatile variable will always be read from the main memory.
 *    not from local thread cache.
 * 3. If you declared variable as volatile, Read and Wright are atomic.
 * 4. It reduce the risk of memory consistency erro.
 * 5. Any write to volatile variable in Java establishes a happen before the relationship with successive reads of that same variable.
 * 6. The volatile variables are always visible to other threads.
 * 7. The volatile variable that is an object reference may be null.
 * 8. When a variable is not shared between multiple threads, you do not need to use the volatile keyword with that variable.
 *
 **/
public class RainJuc {
    private static Logger logger = LoggerFactory.getLogger(RainJuc.class);
    /* volatile */  boolean flag = true;// pls compare the difference of whether there is volatile keyword.

    void run() {
        System.out.println("Starting ... ");
        while (flag) {
            System.out.println("Running ...");
        }
        System.out.println("End ...");
    }

    public static void main(String[] args) {
        RainJuc juc = new RainJuc();
        new Thread(juc::run, "juc").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("Coming ... ");
        juc.flag = false;

    }


}
