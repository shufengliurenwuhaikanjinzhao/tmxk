package com.springcloud.tmxk.desiginmodel.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @ClassName Strategy01
 * @Author Rain
 * @Date 2020/3/10 22:40
 * @Version 1.0
 * @Deacription TODO
 **/
public class Strategy01 {
    private static Logger logger = LoggerFactory.getLogger(Strategy01.class);

    public static void main(String[] args) {
        compare();
        int[] arr = {1, 4, 3, 2};
        sort(arr);
        Arrays.stream(arr).forEach(System.out::print);
        System.out.println();
        Rain[] rain = {new Rain(1, 1),
                new Rain(1, 2),
                new Rain(3, 2),
                new Rain(2, 5),
                new Rain(2, 3)};
        sortRain(rain);
        Arrays.stream(rain).forEach(System.out::print);
    }

    public static void compare() {
        String str = "xxxxxxav";
        int xxx = str.compareTo("xxx");
        System.out.println(xxx);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sortRain(Rain[] rain) {
        for (int i = 0; i < rain.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < rain.length; j++) {
                minIndex = rain[j].compareTo(rain[minIndex]) == -1 ? j : minIndex;
            }
            swapRain(rain, i, minIndex);
        }
    }

    private static void swapRain(Rain[] rain, int i, int j) {
        Rain temp = rain[i];
        rain[i] = rain[j];
        rain[j] = temp;
    }

}
