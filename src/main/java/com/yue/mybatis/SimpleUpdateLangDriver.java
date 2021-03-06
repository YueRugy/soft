package com.yue.mybatis;

import com.google.common.base.CaseFormat;
import com.yue.annotation.Invisible;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;

/**
 * Created by yue on 2017/9/12
 */
public class SimpleUpdateLangDriver extends AbstractLangDriver {


    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        if (matcher(script)) {
            StringBuilder sb = new StringBuilder();
            sb.append("<set>");

            for (Field f : parameterType.getDeclaredFields()) {
                if (f.isAnnotationPresent(Invisible.class)) {
                    continue;
                }

                if ("id".equals(f.getName())) {
                    continue;
                }

                sb.append("<if test=\"").append(f.getName()).append(" != null");
                if (f.getType() == String.class) {
                    sb.append(" and ").append(f.getName()).append(" != ''\"").append(">");
                } else {
                    sb.append("\"").append(">");
                }
                sb.append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName())).
                        append("=").append("#{").append(f.getName()).append("}").append(",").append("</if>");
            }

            //sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append("</set>");
            script = getSql(sb.toString());
            //  System.out.println(script);

        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}
