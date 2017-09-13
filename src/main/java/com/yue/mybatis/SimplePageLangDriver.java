package com.yue.mybatis;

import com.google.common.base.CaseFormat;
import com.yue.annotation.Invisible;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.Field;
import java.util.Date;


/**
 * Created by yue on 2017/9/13
 */
public class SimplePageLangDriver extends AbstractLangDriver {


    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {

        if (matcher(script)) {
            StringBuilder sb = new StringBuilder();
            sb.append("<trim prefix=\"WHERE\" prefixOverrides=\"AND |OR\">");
            for (Field f : parameterType.getDeclaredFields()) {
                if (f.getName().equals("id")) {
                    continue;
                }
                //不支持日期检索
                if (Date.class == f.getType()) {
                    continue;
                }
                //实体类中标有Invisible注解略过
                if (f.isAnnotationPresent(Invisible.class)) {
                    continue;
                }
                //如果是字符串判断空
                sb.append("<if test=\"").append(f.getName())
                        .append("!= null and ");
                Class<?> type = f.getType();
                if (type == String.class) {
                    sb.append(f.getName()).append(" != ''");
                }
                //int double float 及其包装类
                if (type == int.class || type == Integer.class) {
                    sb.append(f.getName()).append(" != 0");
                }

                if (type == double.class || type == Double.class || type == float.class || type == Float.class) {
                    sb.append(f.getName()).append(" != 0.0");
                }
                //补尾
                sb.append("\">");
                //设置中间
                sb.append("and").append(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, f.getName())).append("=")
                        .append(f.getName());
                sb.append("</if>");
            }

            sb.append("</trim>");
            script = getSql(sb.toString());

            System.out.println(script);
        }

        return super.createSqlSource(configuration, script, parameterType);
    }
}
