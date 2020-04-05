package com.springcloud.tmxk.collections;

import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Rain
 * @Date 2020/3/30 22:34
 * @Description TODO
 **/
public class MapTest extends TestCase {
    private static Logger logger = LoggerFactory.getLogger(MapTest.class);

    public void testApp() {
        Map map =new HashMap<String,String>();
        map.put("xx","yy");
        Long lon=2L;
        Long lon1=null;
        String yy = (String) map.get("yy");
        System.out.println((lon+"").equals(lon+""));
        System.out.println(lon+"");
        System.out.println(lon1==null);
        System.out.println(lon==null);
        System.out.println(null==lon);
        System.out.println(null==lon1);
    }
}
