package com.springcloud.tmxk.test;

import com.springcloud.tmxk.entity.User;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

/**
 * @Author Rain
 * @Date 2020/5/15 21:00
 * @Description TODO
 **/
public class OPtionalTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(OPtionalTest.class);

    public void testApp01() {
        User user = new User();
        user=null;
        User user1 = Optional.ofNullable(user).orElse(null);
        System.out.println(user1);
    }
    public void testApp02() {
        User user = new User();
        user.setUserName("Rain");
        User user1 = Optional.ofNullable(user).get();
        System.out.println(user1);
    }

}
