package com.yue.mybatis;

import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by yue on 2017/9/12
 */

@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class PaginationResultSetInterceptor implements Interceptor {

    private final static Logger logger = LogManager
            .getLogger(PaginationStatementInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        DefaultResultSetHandler resultSetHandler = (DefaultResultSetHandler) invocation.getTarget();
        MetaObject metaResultSetHandler = MetaObject.forObject(resultSetHandler, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
        try {
            ParameterHandler parameterHandler = (ParameterHandler) metaResultSetHandler.getValue("parameterHandler");
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

                BoundSql boundSql = (BoundSql) metaResultSetHandler.getValue("parameterHandler.boundSql");
                Configuration configuration = (Configuration) metaResultSetHandler.getValue("configuration");
                Connection connection = (Connection) metaResultSetHandler.getValue("executor.delegate.transaction.connection");

                String originalSql = boundSql.getSql();

                Dialect.Type databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
                Dialect dialect = null;

                switch (databaseType) {
                    case MYSQL:
                        dialect = new MySql5Dialect();
                        break;
                    case ORACLE:
                        // dialect = new OracleDialect();
                        break;
                    case SQLSERVER:
                        //dialect = new SQLServer2005Dialect();
                        break;
                }


                // 修改sql，用于返回总记录数
                String sql = dialect.getCountString(originalSql);
                Long totalRecord = getTotalRecord(connection, sql, parameterHandler);

                Object result = invocation.proceed();
                Page page = new PageImpl((List) result, pagination, totalRecord);


//                // 设置返回对象类型
//                metaResultSetHandler.setValue("mappedStatement.resultMaps[0].type.name", Page.class.getName());

                // 设置返回值
                List<Page> pageList = new ArrayList<>();
                pageList.add(page);

                return pageList;
            }
        } catch (Exception e) {
            throw new Exception("Overwrite SQL : Fail!");
        }

        return invocation.proceed();
    }

    /**
     * 执行 count 操作
     *
     * @param connection       数据库连接
     * @param sql              sql
     * @param parameterHandler 参数设置处理器
     * @return
     */
    private Long getTotalRecord(Connection connection, String sql, ParameterHandler parameterHandler) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            preparedStatement = connection.prepareStatement(sql);
            parameterHandler.setParameters(preparedStatement);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return (Long) JdbcUtils.getResultSetValue(resultSet, 1, Long.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeResultSet(resultSet);
            JdbcUtils.closeStatement(preparedStatement);
        }
        return 0L;
    }


    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
