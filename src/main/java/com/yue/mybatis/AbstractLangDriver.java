package com.yue.mybatis;

import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yue on 2017/9/12
 */
class AbstractLangDriver extends XMLLanguageDriver {
    private final Pattern inPattern = Pattern.compile("\\(#\\{(\\w+)}\\)");


    private Matcher matcher;

    private Matcher getMatcher(String string) {
        return inPattern.matcher(string);
    }

    boolean matcher(String string) {
        matcher = getMatcher(string);
        return matcher.find();
    }

    String getSql(String string) {
        return "<script>" + matcher.replaceAll(string) + "</script>";
    }

}
