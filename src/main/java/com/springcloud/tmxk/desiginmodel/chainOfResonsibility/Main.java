package com.springcloud.tmxk.desiginmodel.chainOfResonsibility;

import com.springcloud.tmxk.desiginmodel.chainOfResonsibility.servlet.ServletMain;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @ClassName ServletMain
 * @Author Rain
 * @Date 2020/3/14 20:48
 * @Version 1.0
 * @Deacription TODO
 **/
public class Main {
    private static Logger logger = LoggerFactory.getLogger(ServletMain.class);

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("此去莫留意 归来依旧新 <xx><xx> Tanghl");

        ArrayList<Filter> filters = new ArrayList<>();
        boolean add = filters.add(new HTMLFIlter());
        if (add == true) return;
        boolean add1 = filters.add(new SensitiveFilter());
        if (add1 == true) return;
        filters.forEach(filter -> {
            filter.doFilter(msg);
        });
        //---------- 分割线 ----------
//      new HTMLFIlter().doFilter(msg);
//      new SensitiveFilter().doFilter(msg);
        System.out.println(msg);
        //---------- 分割线 ----------
        FilterChain chain = new FilterChain();
        chain.add(new HTMLFIlter()).add(new SensitiveFilter());
        chain.doFilter(msg);
        //---------- 分割线 ----------
        Filter chain2 = new FilterChainImp();
        chain.add(new HTMLFIlter()).add(new SensitiveFilter());
        chain.add(chain2);
        chain.doFilter(msg);
    }


}

@Data
class Msg {
    String name;
    String msg;

    @Override
    public String toString() {
        return "Msg{msg=" + msg + ",name=" + name + "}";
    }
}

interface Filter {
    boolean doFilter(Msg msg);
}

class HTMLFIlter implements Filter {
    @Override
    public boolean doFilter(Msg msg) {
        String info = msg.getMsg();
        info = info.replace("xx", "[");
        info = info.replace('>', ']');
        msg.setMsg(info);
        return true;
    }
}

class SensitiveFilter implements Filter {

    @Override
    public boolean doFilter(Msg msg) {
        String info = msg.getMsg();
        info = info.replaceAll("Tanghl", "RAIn");
        msg.setMsg(info);
        return false;
    }
}


class FilterChain {
    ArrayList<Filter> filters = new ArrayList<>();

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    public void doFilter(Msg msg) {
        filters.forEach(filter -> filter.doFilter(msg));
    }
}

class FilterChainImp implements Filter {
    ArrayList<Filter> filters = new ArrayList<>();

    public FilterChainImp add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public boolean doFilter(Msg msg) {
        filters.forEach(filter -> {
            if (filter.doFilter(msg) == false) return;
        });
        return true;
    }
}