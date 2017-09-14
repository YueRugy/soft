package com.yue.mybatis;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Created by yue on 2017/9/12
 */
public class MySql5Dialect {
    public static String getLimitString(String querySqlString, Pageable pageable) {
        querySqlString = soft(pageable, querySqlString);
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int limit = pageable.getPageSize();
        return querySqlString + " limit " + offset + " ," + limit;
    }

    private static String soft(Pageable pageable, String sql) {
        StringBuilder sb = new StringBuilder(sql);

        Sort sort = pageable.getSort();
        if (sort == null) {
            return sql;
        }

        String sortStr = sort.toString().replace(":", " ");

        sb.append(" order by ").append(sortStr);
        // pageable
        return sb.toString();
    }

    public static String getCountString(String querySqlString) {

        int limitIndex = querySqlString.lastIndexOf("limit");

        if (limitIndex != -1) {
            querySqlString = querySqlString.substring(0, limitIndex);
        }

        // 用的过程中会发现这里对原有sql进行包装一层select count会有SQL效率低的问题
        // 等待优化
        return "SELECT COUNT(*) FROM (" + querySqlString + ") tem";
    }

    public boolean supportsLimit() {
        return true;
    }
}
