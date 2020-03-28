package com.springcloud.tmxk.desiginmodel.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName User
 * @Author Rain
 * @Date 2020/3/11 23:27
 * @Version 1.0
 * @Deacription TODO
 **/
@Data
@AllArgsConstructor
public class User implements Comparable<User> {
    private static Logger logger = LoggerFactory.getLogger(User.class);

    int weight, height;

    @Override
    public int compareTo(User user) {
        return xx(user, this.weight, this.height);
    }

    static int xx(User user, int weight, int height) {
        if (weight < user.weight) return -1;
        else if (weight > user.weight) return 1;
        else {
            if (height < user.height) return -1;
            else if (height > user.height) return 1;
            else return 0;
        }
    }
}
