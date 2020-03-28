package com.springcloud.tmxk.desiginmodel.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @ClassName Sorter
 * @Author Rain
 * @Date 2020/3/11 23:21
 * @Version 1.0
 * @Deacription TODO
 **/
@Component
public class Sorter {
    private static Logger logger = LoggerFactory.getLogger(Sorter.class);

    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j].compareTo(arr[minIndex]) == -1 ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
