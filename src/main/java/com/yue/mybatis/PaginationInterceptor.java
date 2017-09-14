package com.yue.mybatis;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created by yue on 2017/9/13
 */
abstract class PaginationInterceptor implements Interceptor {

    final String pageMethodName = ".+ByPage$";

    Pageable getPageable(Object parameterObject) {
        if (parameterObject instanceof Map) {
            Map map = (Map) parameterObject;
            for (Object key : map.keySet()) {
                if (map.get(key) instanceof Pageable) {
                    return (Pageable) map.get(key);
                    //pagination = (Pageable) paramMapObject.get(key);
                }
            }
        }
        return null;
    }

}
