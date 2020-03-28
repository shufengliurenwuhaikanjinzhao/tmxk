package com.springcloud.tmxk.desiginmodel.strategy.rain02;

import com.springcloud.tmxk.desiginmodel.strategy.User;
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
    @Autowired(required = false)
    private Sorter sorter;

    public void testApp() {
        User[] users = {new User(1, 1),
                new User(1, 2),
                new User(3, 2),
                new User(2, 5),
                new User(2, 3)};
        //sorter= new Sorter<>();
        sorter.sort(users, (Comparator<User>) (o1, o2) -> {
            if (o1.getHeight() < o2.getHeight()) return -1;
            else if (o1.getHeight() > o2.getHeight()) return 1;
            else return 0;
        });
        sorter.sort(users, new UserWeightComparator());
        System.out.println(Arrays.toString(users));;
        Arrays.stream(users).forEach(System.out::print);
    }

}
