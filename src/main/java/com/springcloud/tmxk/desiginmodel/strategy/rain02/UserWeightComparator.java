package com.springcloud.tmxk.desiginmodel.strategy.rain02;

import com.springcloud.tmxk.desiginmodel.strategy.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName UserWeightComparator
 * @Author Rain
 * @Date 2020/3/12 0:00
 * @Version 1.0
 * @Deacription TODO
 **/
public class UserWeightComparator implements Comparator<User> {
    private static Logger logger = LoggerFactory.getLogger(UserWeightComparator.class);

    @Override
    public int compareTo(User o1, User o2) {
        if (o1.getWeight() < o2.getWeight()) return -1;
        else if (o1.getWeight() > o2.getWeight()) return 1;
        else return 0;
    }
}
