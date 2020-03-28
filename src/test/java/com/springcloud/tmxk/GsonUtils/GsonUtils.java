package com.springcloud.tmxk.GsonUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.springcloud.tmxk.XStream.User;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.GsonJsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Rain
 * @Date 2020/3/16 16:43
 * @Deacription TODO
 **/
public class GsonUtils {
    private static Logger logger = LoggerFactory.getLogger(GsonUtils.class);

    public <T> T objectToGson(String str, Class<T> cls) {
        Gson gson = new Gson();
        T t = null;
        try {
            //t = gson.fromJson(okstr, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }



    public static <T> String beanToGsonObject(Class<T> cls){
        Gson gson = new Gson();
        String s = gson.toJson(cls);
        JsonObject returnData = new JsonParser().parse(s).getAsJsonObject();
        returnData.remove("");
        return returnData.toString();

    }

}
