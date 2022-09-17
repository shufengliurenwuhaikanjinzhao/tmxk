package com.springcloud.tmxk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * SpringBoot的自动配置：需要给他配置文件的值，它就会自动配置。配置在application.properties文件中
 * 也可以不配置，但是需要声明一下  (exclude = {DataSourceAutoConfiguration.class})
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TmxkApplication {
    public static void main(String[] args) {
        SpringApplication.run(TmxkApplication.class, args);
    }

}
