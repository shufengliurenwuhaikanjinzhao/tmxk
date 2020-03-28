package com.springcloud.tmxk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author tanghl
 * @version v1.0.0
 * @date 2019-12-05
 * @description
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Integer id;
    private String name;
    public Person(String name){
        this.name = name;
    }
}
