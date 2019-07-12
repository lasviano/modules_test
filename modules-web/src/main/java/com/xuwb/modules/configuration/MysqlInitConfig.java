package com.xuwb.modules.configuration;

import com.xuwb.modules.mysql.MysqlInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class MysqlInitConfig {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${mysql.username}")
    private String username;

    @Value("${mysql.password}")
    private String password;

    @Value("${mysql.database}")
    private String database;

    @Autowired
    private MysqlInitializer mysqlInitializer;

    @PostConstruct
    public void mysqlInit(){
        jdbcUrl = jdbcUrl.replaceFirst(database, ""); //connect to instance without database
        try {
            Class.forName(driverClassName);
            Connection connection = DriverManager.getConnection(jdbcUrl,username,password);
            //执行sql，创建数据库
            mysqlInitializer.executeSqlIfNotExistDb(connection,database);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
