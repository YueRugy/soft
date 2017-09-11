package com.yue.mybatis;

import com.google.common.base.CaseFormat;
import com.yue.annotation.Invisible;
import com.yue.entity.User;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by yue on 2017/9/10
 */
public class MybatisUtil {


    private static String createInsert(Class<?> parameterType) {

        StringBuilder sb = new StringBuilder();

        String tableName = parameterType.getSimpleName();

        //类的名字第一个字母小写
        tableName = StringUtils.uncapitalize(tableName);
        //每个大写字母前面加_并且全部小写
        //  System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));
        tableName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, tableName);

        sb.append("insert into ").append(tableName).append("(");
        //没有注解的属性并且每个大写字母前面加_并且全部小写 日期类型如果不是匹配createTime 全部是数据库默认
        for (Field field : parameterType.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Invisible.class)) {
                //去掉id自增长
                if ("id".equals(field.getName())) {
                    continue;
                }
                if (field.getType() == Date.class) {
                    if ("createTime".equals(field.getName())) {
                        String filedName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                        sb.append(filedName).append(",");
                    }
                } else {
                    String filedName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                    sb.append(filedName).append(",");
                }

            }
        }
        //去掉最后一个,
        sb.deleteCharAt(sb.lastIndexOf(",")).append(")").append(" VALUES ").append("(");
        for (Field f : parameterType.getDeclaredFields()) {
            if (!f.isAnnotationPresent(Invisible.class)) {
                //去掉id自增长
                if ("id".equals(f.getName())) {
                    continue;
                }
                if (f.getType() == Date.class) {
                    if ("createTime".equals(f.getName())) {
                        sb.append("now()").append(",");
                    }
                } else {
                    sb.append("#{").append(f.getName()).append("}").append(",");
                }
            }
        }


        sb.deleteCharAt(sb.lastIndexOf(",")).append(")");

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(createInsert(User.class));
    }


}
