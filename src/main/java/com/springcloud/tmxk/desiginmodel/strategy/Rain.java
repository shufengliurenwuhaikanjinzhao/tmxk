package com.springcloud.tmxk.desiginmodel.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName Rain
 * @Author Rain
 * @Date 2020/3/10 22:59
 * @Version 1.0
 * @Deacription TODO
 **/
public class Rain {
    private static Logger logger = LoggerFactory.getLogger(Rain.class);
    int weight, height;

    public Rain(int weight, int height) {
        this.weight = weight;
        this.height = height;
    }

    public int compareTo(Rain rain) {
        return xx(rain, this.weight, this.height);
    }

    static int xx(Rain rain, int weight, int height) {
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
        return "Rain{" +
                "weight=" + weight +
                ", height=" + height +
                '}';
    }
}
