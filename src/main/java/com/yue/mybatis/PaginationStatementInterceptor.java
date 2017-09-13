package com.yue.mybatis;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
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

        System.out.println("aaaaaaaaaaaaaaaa----------");

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        //  String sql = statementHandler.getBoundSql().getSql();

        ParameterHandler parameterHandler = statementHandler.getParameterHandler();
        //获取执行的方法名
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        String methodName = mappedStatement.getId();
        methodName = "findAllByPage";
        System.out.println(methodName.matches(pageMethodName));
        Object parameterObject = parameterHandler.getParameterObject();

        Pageable pagination = null;

        if (parameterObject instanceof MapperMethod.ParamMap) {

            MapperMethod.ParamMap paramMapObject = (MapperMethod.ParamMap) parameterObject;


            for (Object key : paramMapObject.keySet()) {
                if (paramMapObject.get(key) instanceof Pageable) {
                    pagination = (Pageable) paramMapObject.get(key);
                    break;
                }
            }
        }

        if (pagination != null) {

            MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
            Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
            Dialect.Type databaseType;

            try {
                databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
            } catch (Exception e) {
                throw new Exception("Generate SQL: Obtain DatabaseType Failed!");
            }

            Dialect dialect = null;
            switch (databaseType) {
                case MYSQL:
                    dialect = new MySql5Dialect();
                    break;
                case ORACLE:
                    //  dialect = new OracleDialect();
                    break;
                case SQLSERVER:
                    // dialect = new SQLServer2005Dialect();
                    break;
            }

            String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

            metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getLimitString(originalSql, pagination.getPageNumber() * pagination.getPageSize(), pagination.getPageSize()));

            if (logger.isDebugEnabled()) {
                BoundSql boundSql = statementHandler.getBoundSql();
                logger.debug("Generate SQL : " + boundSql.getSql());
            }

            return invocation.proceed();
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
