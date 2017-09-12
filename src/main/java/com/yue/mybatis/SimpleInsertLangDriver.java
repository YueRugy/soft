package com.yue.mybatis;

import com.google.common.base.CaseFormat;
import com.yue.annotation.Invisible;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by yue on 2017/9/12
 */
public class SimpleInsertLangDriver extends AbstractLangDriver {


    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        if (matcher(script)) {
            StringBuilder sb = new StringBuilder();
            StringBuilder value = new StringBuilder();
            sb.append("(");

            for (Field f : parameterType.getDeclaredFields()) {
                if (f.isAnnotationPresent(Invisible.class)) {
                    continue;
                }

                if (f.getName().equals("id")) {
                    continue;
                }
                if (f.getType() == Date.class) {
                    if (f.getName().equals("createTime")) {
                        sb.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName())).append(",");
                        value.append("now()").append(",");
                    }
                }

                sb.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName())).append(",");
                value.append("#{").append(f.getName()).append("}").append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            value.deleteCharAt(value.lastIndexOf(","));
            sb.append(") ").append("VALUES (").append(value.toString()).append(")");
            script = getSql(sb.toString());
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}
