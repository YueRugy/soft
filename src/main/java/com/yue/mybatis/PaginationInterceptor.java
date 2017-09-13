package com.yue.mybatis;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.data.domain.Pageable;

/**
 * Created by yue on 2017/9/13
 */
public abstract class PaginationInterceptor implements Interceptor {

    final String pageMethodName = ".+ByPage$";

    Pageable getPageable(Object parameterObject) {
        if (parameterObject instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap paramMapObject = (MapperMethod.ParamMap) parameterObject;
            for (Object key : paramMapObject.keySet()) {
                if (paramMapObject.get(key) instanceof Pageable) {
                    return (Pageable) paramMapObject.get(key);
                    //pagination = (Pageable) paramMapObject.get(key);
                }
            }
        }
        return null;
    }

}
