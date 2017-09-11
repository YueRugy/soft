package com.yue.component;

import com.yue.filter.DruidStatFilter;
import com.yue.servlert.DruidStatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yue on 2017/9/8
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    /*Interceptors*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                return super.preHandle(request, response, handler);
            }
        }).addPathPatterns("/**");


    }

    /*filter*/
    @Bean
    @Order(1)
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

  /*  @Bean
    @Order(2)
    public TestFilter testFilter() {
        return new TestFilter();
    }*/

    @Bean
    @Order(3)
    public DruidStatFilter druidStatFilter() {
        return new DruidStatFilter();
    }

    /*servlet*/
    @Bean
    public ServletRegistrationBean getDemoServlet() {
        DruidStatViewServlet demoServlet = new DruidStatViewServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(demoServlet);
        List<String> urlMappings = new ArrayList<String>();
        urlMappings.add("");////访问，可以添加多个
        registrationBean.setUrlMappings(urlMappings);
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

}

