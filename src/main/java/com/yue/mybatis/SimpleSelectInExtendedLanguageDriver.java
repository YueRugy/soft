package com.yue.mybatis;

import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yue on 2017/9/9
 */
public class SimpleSelectInExtendedLanguageDriver extends AbstractLangDriver {


    @Override
    public SqlSource createSqlSource(Configuration configuration, String script, Class<?> parameterType) {
        if (matcher(script)) {
            script = getSql("(<foreach collection=\"$1\" item=\"item\" separator=\",\" >#{item}</foreach>)");

        }
        return super.createSqlSource(configuration, script, parameterType);
    }


}
