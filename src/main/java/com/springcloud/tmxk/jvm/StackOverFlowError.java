package com.springcloud.tmxk.jvm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName StackOverFlowError
 * @Author Rain
 * @Date 2020/3/1 11:18
 * @Version 1.0
 * @Deacription 默认情况下：count=6673
 * 设置栈大小： -Xss256k:count=1741  java.lang.StackOverflowError
 **/
public class StackOverFlowError {
    private static Logger logger = LoggerFactory.getLogger(StackOverFlowError.class);
    private static int count = 1;

    public static void main(String[] args) {
        logger.info(count + "");
        count++;
        main(args);

    }

}
