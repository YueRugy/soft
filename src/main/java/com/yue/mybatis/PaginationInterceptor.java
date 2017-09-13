package com.yue.mybatis;

import org.apache.ibatis.plugin.Interceptor;

/**
 * Created by yue on 2017/9/13
 */
public abstract class PaginationInterceptor implements Interceptor {

    final String pageMethodName = ".+ByPage$";

}
