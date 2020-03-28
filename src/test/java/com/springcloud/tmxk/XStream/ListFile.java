package com.springcloud.tmxk.XStream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName ListFile
 * @Author Rain
 * @Date 2020/2/24 12:43
 * @Version 1.0
 * @Deacription TODO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListFile {
    List<String> files;
    @Override
    public String toString(){
        return "ListFile{"+"files="+files+"}";
    }
}
