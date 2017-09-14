package com.yue.component;

import com.yue.mybatis.PaginationResultSetInterceptor;
import com.yue.mybatis.PaginationStatementInterceptor;
import com.yue.mybatis.SqlInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by yue on 2017/9/13
 */
@Configuration
@EnableTransactionManagement
public class MybatisConfig implements TransactionManagementConfigurer {
    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Override
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.yue.entity");//扫描entity包 使用别名

        // bean.setTypeAliasesPackage("tk.mybatis.springboot.model");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);//自动使用驼峰命名属性映射字段
        configuration.setUseGeneratedKeys(true);//使用jdbc的getGeneratedKeys获取数据库自增主键值

        //分页插件

        PaginationStatementInterceptor paginationStatementInterceptor = new PaginationStatementInterceptor();
        PaginationResultSetInterceptor paginationResultSetInterceptor = new PaginationResultSetInterceptor();
        SqlInterceptor sqlInterceptor = new SqlInterceptor();
        configuration.addInterceptor(paginationStatementInterceptor);
        configuration.addInterceptor(paginationResultSetInterceptor);
        configuration.addInterceptor(sqlInterceptor);
        bean.setConfiguration(configuration);
        // PageHelper pageHelper = new PageHelper();
      /*  Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        paginationStatementInterceptor.setProperties(properties);
        paginationResultSetInterceptor.setProperties(properties);*/

        //添加插件
        //   bean.setPlugins(new Interceptor[]{paginationStatementInterceptor, paginationResultSetInterceptor});

        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
