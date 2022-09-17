package com.springcloud.tmxk.test;


import com.alibaba.fastjson.JSONObject;
import com.springcloud.tmxk.entity.Person;
import com.springcloud.tmxk.entity.User;
import com.thoughtworks.xstream.XStream;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName AppTest
 * @Deacription TODO
 * @Author thl
 * @Date 2019/11/2 11:27
 * @Version 1.0
 **/
@Service
public class AppTest extends TestCase {
    @Autowired
    private HashMapTest hashMapTest;

    public void testApp002() {
        String s = "a...a";// 共65534个a

        System.out.println(s.length());

        String s1 = "aaa.........aaaaaaaaa";// 共65535个a
        String s2 = new String();// 共65535个a
        double d = 1 / (1 - 1);
        System.out.println(s1.length());


    }

    public void testApp001() {
        HashMap hashMap = new HashMap();

        hashMap.put("x", "x");
        hashMap.put("y", "y");
        hashMap.put("z", "z");
        Object object = 31111;
        System.out.println(object.hashCode() >>> 1);
        System.out.println(2 ^ 1);

    }

    public void testAppx() {
        String str = "32.234";
        System.out.println(str.split("\\."));

    }

    public void testApp2() {
        JSONObject jo;
        try {
            int num = 1 / 0;
        } catch (Exception e) {
            jo = new JSONObject();
            System.out.println(jo.getString("x"));
        }

    }

    public void testApp3() {
        JSONObject jo = new JSONObject();
        Person person = this.getPerson(jo);
        System.out.println(jo.toJSONString());
        System.out.println(person.getName());

    }

    public Person getPerson(JSONObject jo) {
        jo.put("xx", "xx");
        return new Person("thl");
    }

    public void testApp4() {
        JSONObject jo = new JSONObject();
        this.add(jo);
        System.out.println(jo);

    }


    public void add(JSONObject jo) {
        jo.put("xx", "xxx");
        jo.put("yy", "yyy");
        if (jo.containsKey("xx")) {
            return;
        }
        System.out.println("xxxxxxxxxxxxxxxx");
    }

    public void testApp5() {
        long number = 1L & 2L;
        System.out.println(number);

    }

    public void testApp6() {
        List<User> list = new ArrayList<>();
        User user = new User();
        user.setUserName("xx");
        list.add(user);
        for (User u : list) {
            u.setUserName("yy");
        }
        System.out.print(list.get(0).getUserName());


    }

    public void testApp7() {
        Set<Long> set = new HashSet<Long>();
        xx(set);
        System.out.println(set.toString());

    }

    private void xx(Set<Long> set) {
        set.add(1L);
        set.add(2L);
        set.add(3L);
    }

    public void testApp8() {
//        String rain = "ra  in";//-940675116
        System.out.println(System.currentTimeMillis());
        String rain = "r12333333333333331212" + System.currentTimeMillis();//3492756
        System.out.println(myHashCode(rain));
        System.out.println(rain.hashCode());

    }
    private static long myHashCode(String str) {
        long h = 0;
        if (h == 0) {
            int off = 0;
            char val[] = str.toCharArray();
            long len = str.length();

            for (long i = 0; i < len; i++) {
                h = 31 * h + val[off++];
            }
        }
        return h;
    }

}

