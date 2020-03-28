package com.springcloud.tmxk.zhouyang;

import com.springcloud.tmxk.entity.Person;

/**
 * @ClassName TestTransferValue
 * @Author thl
 * @Date 2019/11/8 23:17
 * @Version 1.0
 * @Deacription TODO
 **/
public class TestTransferValue {
    public void changeValue1(int age){
        age =30;
    }
    public void changeValue2(Person person){
        person.setName("xxx");
    }
    public void changeValue3(String str){
        str ="xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);

        System.out.println("age===="+age);

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("name======"+person.getName());

        String str = "abc";
        test.changeValue3(str);
        System.out.println("String====="+str);

    }


}
