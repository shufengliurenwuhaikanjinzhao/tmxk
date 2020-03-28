package com.springcloud.tmxk.desiginmodel.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * @ClassName TestCase
 * @Author Rain
 * @Date 2020/3/11 23:19
 * @Version 1.0
 * @Deacription TODO
 **/
public class TestCase extends junit.framework.TestCase {
    private static Logger logger = LoggerFactory.getLogger(TestCase.class);
    @Autowired
    private Sorter sorter;

    public void testApp() {
        RainComparable[] rain = {new RainComparable(1, 1),
                new RainComparable(1, 2),
                new RainComparable(3, 2),
                new RainComparable(2, 5),
                new RainComparable(2, 3)};
        sorter.sort(rain);
        Arrays.stream(rain).forEach(System.out::print);
        User[] users = {new User(1, 1),
                new User(1, 2),
                new User(3, 2),
                new User(2, 5),
                new User(2, 3)};
        sorter.sort(users);
        Arrays.stream(users).forEach(System.out::print);
    }

}
