package com.yue.mybatis;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by yue on 2017/9/12
 */


@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PaginationStatementInterceptor extends PaginationInterceptor {
    private final static Logger logger = LogManager
            .getLogger(PaginationStatementInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        System.out.println("111111111111111111111");

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //  String sql = statementHandler.getBoundSql().getSql();

        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        //获取执行的方法名
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String methodName = mappedStatement.getId();
        // methodName = methodName.substring(methodName.lastIndexOf(","), methodName.length() - 1);
        //  System.out.println(methodName.matches(pageMethodName));

        //如果符合命名规范
        if (methodName.matches(pageMethodName)) {
            Object parameterObject = parameterHandler.getParameterObject();
            //如果只有一个参数
            Pageable pagination;
            if (parameterObject instanceof Pageable) {
                pagination = (Pageable) parameterObject;
            } else {
                pagination = getPageable(parameterObject);
            }


            if (pagination != null) {
                String originalSql = (String) metaObject.getValue("delegate.boundSql.sql");
                String sql = MySql5Dialect.getLimitString(originalSql, pagination);
                metaObject.setValue("delegate.boundSql.sql", sql);

                if (logger.isDebugEnabled()) {
                    BoundSql boundSql = statementHandler.getBoundSql();
                    logger.debug("Generate SQL : " + boundSql.getSql());
                }

                return invocation.proceed();
            }
        }


        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
