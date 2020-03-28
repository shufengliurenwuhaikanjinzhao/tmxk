package com.springcloud.tmxk;

import junit.framework.TestCase;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName AppTest
 * @Deacription TODO
 * @Author thl
 * @Date 2019/11/2 11:27
 * @Version 1.0
 **/
public class AppTest extends TestCase {
    private static void accept(Language l) {
        System.out.println();
    }

    @Data
    static
    class User {
        private Long id;
        private String name;
        private Integer age;
        private Date date;

        public User() {
        }

        public User(Long id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + ", date=" + date + '}';
        }
    }

    List<User> list = Stream.of(new User(1L, "a", 1), new User(2L, "c", 2), new User(3L, "b", 3), new User(4L, "a", 3)).collect(Collectors.toList());
    static Map<String, String> map = new HashMap<String, String>() {
        {
            put("1", "aaa");
            put("2", "ccc");
            put("3", "bbb");
        }
    };

    public void testApp() {
        //list.forEach(userBean -> System.out.println("hello," + userBean.getUsername()));
        User user1 = new User();
        // 循环输出列表值
        int num = 0;
        list.forEach(user -> {
            int number = num;
            number++;
            System.out.println(num);
            user1.setAge(1);
        });

    }

    //重新封装集合数据
    public void testApp1() {
        //list.stream().map(userBean -> new UserBean(userBean.getUserid(), userBean.getWorkid(), userBean.getUsername())).collect(Collectors.toList());
        //userList.forEach(userBean -> System.out.println("new list:" + userBean.getUsername()));
        List<User> userList = list.stream().map(user -> new User()).collect(Collectors.toList());
        userList.forEach(user -> System.out.println(user.toString()));
    }

    public void testApp2() {
        //按userid排序
        //List<UserBean> sortList = list.stream().sorted((userBean1,userBean2) -> userBean1.getUsername().compareTo(userBean2.getUsername())).collect(Collectors.toList());
        //sortList.forEach(userBean -> System.out.println("sortList:" + userBean.getUserid() + "," + userBean.getUsername()));
        list = list.stream().sorted((user1, user2) -> user2.getName().compareTo(user1.getName())).collect(Collectors.toList());
        list.forEach(user -> System.out.println(user.toString()));
    }

    // 多条件排序
    public void testApp3() {
        //list.sort(Comparator.comparing(UserBean::getUserid).thenComparing(UserBean::getUsername));
        //list.forEach(userBean -> System.out.println("multiSortList:" + userBean.getUserid() + "," + userBean.getUsername()));
        list.sort(Comparator.comparing(User::getAge).thenComparing(User::getName));
        list.forEach(user -> System.out.println(user.toString()));
    }

    public void testApp4() {
        //第一种写法
        Comparator<User> comparator = (user1, user2) -> user1.getName().compareTo(user2.getName());
        list.sort(comparator.reversed());
        //第二种写法
        //list.sort(Comparator.comparing(User::getName).reversed());

    }

    // 多条件倒序
    public void testApp5() {
        list.sort(Comparator.comparing(User::getAge).thenComparing(User::getName).reversed());
    }

    // 集合分组
    public void testApp6() {
        Map<Long, List<User>> groupByMap = list.stream().collect(Collectors.groupingBy(User::getId));
        groupByMap.forEach((k, v) -> System.out.println(k + "," + v));
//        1,[User{id=1, name='a', age=1, date=Mon Oct 14 17:08:29 CST 2019}]
//        2,[User{id=2, name='c', age=2, date=Wed Oct 16 17:08:29 CST 2019}]
//        3,[User{id=3, name='b', age=3, date=Tue Oct 15 17:08:29 CST 2019}]
//        4,[User{id=4, name='a', age=3, date=Tue Oct 15 17:08:29 CST 2019}]
    }

    //  求和
    public void testApp7() {
        System.out.println("sum=" + list.stream().mapToInt(User::getAge).sum());
        //sum=9
    }

    // 求最大值
    public void testApp8() {
        OptionalInt optional = list.stream().mapToInt(User::getAge).max();
        System.out.println("max=" + optional.getAsInt());
        //max=3
    }

    // 求最小值
    public void testApp9() {
        OptionalInt optional = list.stream().mapToInt(User::getAge).min();
        System.out.println("min=" + optional.getAsInt());
        //min=1
    }

    // 求平均值
    public void testApp10() {
        OptionalDouble optionalDouble = list.stream().mapToInt(User::getAge).average();
        System.out.println("average=" + optionalDouble.getAsDouble());
        //average=2.25
    }

    // List 转map
    public void testApp11() {
        //用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
        Map<String, User> map = list.stream().collect(Collectors.toMap(User::getName, user -> user, (k1, k2) -> k1));
        map.forEach((k, v) -> System.out.println("k=" + k + ",v=" + v));
//        k=a,v=User{id=1, name='a', age=1, date=Mon Oct 14 17:27:16 CST 2019}
//        k=b,v=User{id=3, name='b', age=3, date=Tue Oct 15 17:27:16 CST 2019}
//        k=c,v=User{id=2, name='c', age=2, date=Wed Oct 16 17:27:16 CST 2019}
    }

    //Map转List
    public void testApp12() {
        List<User> list = map.entrySet().stream().sorted(Comparator.comparing(key -> key.getKey())).map(key -> new User(Long.valueOf(key.getKey()), key.getValue(), Integer.valueOf(key.getKey()))).collect(Collectors.toList());
        list.forEach(userBean -> System.out.println(userBean.getId() + "," + userBean.getName()));
//        1,aaa
//        2,ccc
//        3,bbb
    }

    // 字符串转List
    public void testApp13() {
        String str = "\"1\",\"2\",\"3\"";
        //不需要处理
        //List<String> list = Arrays.asList(str.split("\\,"));
        //需要处理
        List<String> list = Arrays.asList(str.split(",")).stream().map(string -> String.valueOf(string)).collect(Collectors.toList());
        list.forEach(string -> System.out.println(string));
    }

    public enum Language {
        en("Tom"),
        zh("Bob"),
        es("Jane");

        Language(String tom) {

        }
    }

    // List字符串转 set
    public Set<Language> testApp14() {
        String[] staffs = new String[]{"Tom", "Bob", "Jane"};
        List staffsList = Arrays.asList(staffs);

        Set result = (Set<Language>) new HashSet(staffsList);
        System.out.printf(String.valueOf(result.size()));
        System.out.println(result.contains("Tom"));
        return new HashSet<>(null);
    }

    public void testApp15() {
        Set<Language> languages = testApp14();
        System.out.println(languages.size());
    }

    public void testApp16() {
        User user = new User();
        user.setAge(11);
        user.setId(1123L);
        System.out.println(user.toString());
    }

    public void testApp17() {
        String[] str =new String[2];
        str[0]=null;
        str[1]="xx";
        List<String[]> list = new ArrayList<>();
        list.add(str);
        Map<String,String> map = new HashMap<>();
        for (String[] str1:list) {
            map.put(str1[0],str1[1]);
        }
        System.out.println(map.get(null));
    }
    public void testApp18() {
        User user = new User(1L,null,null);
        System.out.println(user.getName());
    }
    // 测试List是否款徐
    public void testApp19() {
       List list = new ArrayList();
       list.add(1);
       list.add(4);
       list.add(3);
       list.add(2);

        System.out.println(list.toString());
    }


    public void testApp20() {
        Map map = new HashMap<String,List<String>>();
        List list = new ArrayList();
        map.put(1,list);
        map.put(2,list);
        list.add(1);
        list.add(4);
        list.add(3);
        list.add(2);

        System.out.println(map);
    }
}

