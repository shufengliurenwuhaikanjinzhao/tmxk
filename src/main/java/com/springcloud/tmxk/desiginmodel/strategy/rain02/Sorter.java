package com.springcloud.tmxk.desiginmodel.strategy.rain02;

import com.springcloud.tmxk.desiginmodel.strategy.Comparable;
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
public class Sorter<T> {
    private static Logger logger = LoggerFactory.getLogger(Sorter.class);

    public void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = comparator.compareTo(arr[j], arr[minIndex]) == -1 ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
