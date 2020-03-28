package com.springcloud.tmxk.XStream;

import com.thoughtworks.xstream.XStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName Use
 * @Author Rain
 * @Date 2020/2/24 12:37
 * @Version 1.0
 * @Deacription TODO
 **/
@Data
@Accessors(chain = true)
public class User {
    private String userName;

    private String email;
    private ListFile files;

    public User() {}

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String toString() {
        return "User:{userName=" + this.userName + ",email=" + this.email + ",files="+ files +"}";
    }

}
