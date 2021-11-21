package com.laioffer.onlineOrder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ejb.access.LocalStatelessSessionProxyFactoryBean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.util.Properties;

// 3 important interfaces in Hibernate
//  SessionFactory:
//  it is a heavyweight object which usually is created during application start up for connecting to
//  a database and kept for later use. It also services clients to obtain Session instances from this factory.
//  (获得 Session 对象)
//  Session:
//  this is the central API class abstracting the notion of a persistence service, the main function of
//  the Session is to offer create, read and delete operations for instances of mapped entity classes.
//  (通过 Session 来对数据库进行增删改查的操作)
//  Transaction:
//  allows the application to define units of work. (保证代码的一致性)

@Configuration      // Spring
@EnableWebMvc       // Spring MVC
// Initiate dispatcherServlet 时要如何config application
public class ApplicationConfig {
    @Bean(name="sessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.laioffer.onlineOrder.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean(name="dataSource")
    public DataSource dataSource() {
        String RDS_INSTANCE = "laiproject-instance.cij11gfnaahw.us-east-2.rds.amazonaws.com";
        String USERNAME = "";
        String PASSWORD = "";
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + RDS_INSTANCE +
                ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        return hibernateProperties;
    }

}
