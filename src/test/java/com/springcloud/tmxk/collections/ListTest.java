package com.springcloud.tmxk.collections;

import com.springcloud.tmxk.entity.User;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Rain
 * @Date 2020/4/2 0:02
 * @Description TODO
 **/
public class ListTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(MapTest.class);

    public void testApp() {
        List<User> list = new ArrayList<>();
        list.add(new User("xx","yy"));
        list.add(new User("11","22"));
        list.add(new User("33","44"));
        List<User> list1 = new ArrayList<>();
        for (User user:list) {
            if(user.getUserName()=="xx"){
                list1.add(user);
            }
        }
        list1.forEach(user -> System.out.println(user.getUserName()));


    }
}
