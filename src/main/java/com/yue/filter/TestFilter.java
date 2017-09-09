package com.yue.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Created by yue on 2017/9/8
 */
@Order(2)
@WebFilter(filterName = "testFilter", urlPatterns = "/*")
public class TestFilter extends AdapterFilter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("TestFilter1");
        chain.doFilter(request, response);
    }


}
