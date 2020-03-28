package com.springcloud.tmxk.desiginmodel.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @ClassName Rain
 * @Author Rain
 * @Date 2020/3/11 22:59
 * @Version 1.0
 * @Deacription TODO
 **/
public class RainComparable implements Comparable<RainComparable> {
    private static Logger logger = LoggerFactory.getLogger(RainComparable.class);
    int weight, height;

    public RainComparable(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    @Override
    public int compareTo(RainComparable rainComparable) {
        return xx(rainComparable, this.weight, this.height);
    }

    static int xx(RainComparable rain, int weight, int height) {
        if (weight < rain.weight) return -1;
        else if (weight > rain.weight) return 1;
        else {
            if (height < rain.height) return -1;
            else if (height > rain.height) return 1;
            else return 0;
        }
    }

    @Override
    public String toString() {
        return "RainComparable{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }

}
