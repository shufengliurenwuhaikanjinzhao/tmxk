package com.springcloud.tmxk.juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 * 1 两个数据结构
 *   1.1 stack FILO
 *   1.2 队列先进先出
 *   1.3 总结
 * 2 阻塞队列
 *   2.1 阻塞 必须要阻塞  不得不阻塞
 * 3 How?
 *
 **/
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
        // 第一组跑出异常 add remove element
        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);
       //  blockingQueue.add(3);// Exception in thread "main" java.lang.IllegalStateException: Queue full
        System.out.println("==1==");
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        //System.out.println(blockingQueue.remove()); //Exception in thread "main" java.util.NoSuchElementException
        System.out.println("==2==");
        System.out.println(blockingQueue.add(1));//true
        System.out.println(blockingQueue.add(2)); //true
        System.out.println(blockingQueue.element());// 1 检查队列首元素*/
        blockingQueue.remove();
        blockingQueue.remove();
        System.out.println("==3==");
        // 第二组  特殊值 offer  poll peek
        System.out.println(blockingQueue.offer(1));// true
        System.out.println(blockingQueue.offer(2));// true
        System.out.println(blockingQueue.offer(3));// true
        System.out.println(blockingQueue.offer(4));// false
        System.out.println(blockingQueue.offer(5));// false
        System.out.println(blockingQueue.poll());//1
        System.out.println(blockingQueue.poll());//2
        System.out.println(blockingQueue.poll());//3
        System.out.println(blockingQueue.poll());//null
        System.out.println(blockingQueue.peek());// null  @return the head of this queue, or {@code null} if this queue is empty
        // 第三组  阻塞   put take
        System.out.println("==4==");
        blockingQueue.put(1);
        blockingQueue.put(2);
        blockingQueue.put(3);
        System.out.println(blockingQueue.take()); // @return the head of this queue
        blockingQueue.put(4);
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //System.out.println(blockingQueue.take());// Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
        // 第四组 阻塞超时   offer poll
        System.out.println("==5==");
        blockingQueue.offer(1,2, TimeUnit.SECONDS);
        blockingQueue.offer(2,2, TimeUnit.SECONDS);
        blockingQueue.offer(3,2, TimeUnit.SECONDS);
        // 超时离开 等待2秒之后 发现没有空位置  离开
        System.out.println(blockingQueue.offer(4,2, TimeUnit.SECONDS)); // Inserts the specified element into this queue, waiting up to the specified wait time if necessary for space to become available.
        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));//Retrieves and removes the head of this queue, waiting up to the  specified wait time if necessary for an element to become available.
        System.out.println(blockingQueue.offer(4,2, TimeUnit.SECONDS));
    }


}
