package com.springcloud.tmxk.juc.zhouyang;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 *    1 标准访问  先打印邮件还是短信?
 *    2 邮件暂停4秒 先打印邮件还是先打印短信？
 *    3 新增一个普通方法helo() 先打印邮件还是hello
 *    4 两部手机，请问先打印邮件还是短信？
 *    5 两个静态同步方法，同一部手机。 先打印邮件还是短信？
 *    6 两个静态同步方法，2部手机。 先打印邮件还是短信？
 *    7 一个普通方法,1个静态同步方法，1部手机。 先打印邮件还是短信？
 *    8 一个普通方法,1个静态同步方法，2部手机。 先打印邮件还是短信？
 */

import java.util.concurrent.TimeUnit;

/**    一个对象里面如果有多个synchronized方法，某一时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *    其他的线程只等等待，换言之，某一时刻内，只能有唯一一个线程去访问这些synchronized方法
 *    锁的是当前对象this，被锁定后，其他的线程都不能进入到当前对象的其他的synchronized方法
 *
 *    加一个普通方法后和同步锁无关
 *    换成两个锁后，不是同一把锁了，情况立刻变化。
 *
 *    都换成静态同步方法后，又有变化
 *    new this，具体的一部部手机
 *    静态class，唯一的一个模板
 *
 *    所有的非静态同步方法用的都是同一把锁--实例对象本身
 *
 *    synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 *    具体表现为以下3中方式:
 *    对于普通同步方法，锁是当前实例对象
 *    对于静态同步方法 锁是当前的class对象
 *    对于同步方法快，锁是synchronized括号里配置的对象。
 *
 *    当一个线程试图访问同步代码块时，首先必须得到锁，退出或抛出异常时必须释放锁。
 *
 *    也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁
 *    的方法释放锁之后才能获取锁，
 *    所以不用等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁
 *
 *    所有静态同步方法用的也是同一把锁-类对象本身
 *    这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法都必须等待该方法释放锁之后才能获取锁，
 *    而不管是同一个实例对象的静态方法之间，还是不同的实例对象的静态同步方法之间，只要他们是同一个类的
 *    实例对象
 **/
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                 phone.sendSMS();
                //phone.hello();
                //phone2.hello();
                //phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
class Phone{
    public synchronized void sendEmail() throws InterruptedException
    {
        //TimeUnit.SECONDS.sleep(4);
        System.out.println("===== sendEmail");
    }
//     public synchronized void sendSMS() throws InterruptedException
//     {
//         System.out.println("===== sendSMS");
//     }
   /* public static synchronized void sendEmail() throws InterruptedException
    {
        //TimeUnit.SECONDS.sleep(4);
        System.out.println(" ===== senEmail ");
    }*/
    public static synchronized void sendSMS() throws InterruptedException
    {
        System.out.println("===== sendSMS");
    }
    public  void hello()
    {
        System.out.println("=====hello");
    }


}
