package com.springcloud.tmxk.desiginmodel.chainOfResonsibility.servlet;

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
public class ServletMain {
    private static Logger logger = LoggerFactory.getLogger(ServletMain.class);

    public static void main(String[] args) {

        Request request = new Request();
        request.setReq("request T");
        Response response = new Response();
        response.setRes("response T");
        FilterChain chain = new FilterChain();
        chain.add(new FirstFilter()).add(new SecondFilter());
        chain.doFilter(request, response, chain);
        System.out.println(request.req);
        System.out.println(response.res);

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
    boolean doFilter(Request request, Response response, FilterChain chain);
}

class FirstFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response, FilterChain chain) {
        request.setReq( request.getReq().replace("<", "[").replace('>', ']').replaceAll("T", "Rain")+"-> FirstRequest ");
        chain.doFilter(request, response, chain);
        response.res += "-> FirstRequest ";
        return true;
    }
}

class SecondFilter implements Filter {
    @Override
    public boolean doFilter(Request request, Response response, FilterChain chain) {
        request.setReq(request.getReq().replace("<", "[").replace('>', ']').replaceAll("T", "Rain")+"-> SecondFilter");
        chain.doFilter(request, response, chain);
        response.res += "-> SecondFilter ";
        return true;
    }
}

class FilterChain implements Filter {
    ArrayList<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain add(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public boolean doFilter(Request request, Response response, FilterChain chain) {
        if (index == filters.size()) return false;
        Filter filter = filters.get(index);
        index++;
        return filter.doFilter(request, response, chain);
    }
}

@Data
class Request {
    String req;
}

@Data
class Response {
    String res;
}