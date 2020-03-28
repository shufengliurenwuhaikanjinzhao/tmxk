package com.springcloud.tmxk.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 *  证明集合类非安全
 *   1 故障现象
 *     java.util.ConcurrentModificationException
 *   2 导致原因
 *   3 解决方案
 *      3.1 new Vector();
 *      3.2 Collections.synchronizedList(new ArrayList<>())
 *      3.3 new CopyOnWriteArrayList<>();
 **/
/**
 *
 * 写时复制
 *  CopyOnWrite容器即写时复制容器。向一个容器中添加元素的时候，不直接往当前容器Object[]
 *  添加，而是先将当前容器Object[] 进行Copy复制出一个新的容器Object[] new Elements，然后
 *  新的容易Object[] new Elements里添加元素，添加元素之后，再将元容器的应用指向新的容器setArray(newElements);
 *  这样做的好处在于可以对CopyOnWrite容器进行并发的读取而不需要加锁，因为当前容器不会添加任何元素
 *  所以CopyOnWrite容器内国企也是一种读写分离的思想,读和写在不同的容器
 *
 *
 *     public boolean add(E e) {
 *         final ReentrantLock lock = this.lock;
 *         lock.lock();
 *         try {
 *             Object[] elements = getArray();
 *             int len = elements.length;
 *             Object[] newElements = Arrays.copyOf(elements, len + 1);
 *             newElements[len] = e;
 *             setArray(newElements);
 *             return true;
 *         } finally {
 *             lock.unlock();
 *         }
 *     }
 */
public class NotSafeDemo {
    public static void main(String[] args) {
        listNotSafe();

    }

    private static void listNotSafe() {
        //List<String> list = new ArrayList<>();// java.util.ConcurrentModificationException
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //Set<String> set = new HashSet<>();//java.util.ConcurrentModificationException
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void mapNotSafe() {
        // Map<String,String> map = new ConcurrentHashMap<>();
        Map<String,String> map = new HashMap<>();//java.util.ConcurrentModificationException
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName()+"\t ", UUID.randomUUID().toString().substring(8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

}
