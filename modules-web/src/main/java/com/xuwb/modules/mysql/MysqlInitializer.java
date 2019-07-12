package com.xuwb.modules.mysql;

import org.springframework.stereotype.Component;

import java.sql.Connection;

@Component
public class MysqlInitializer {

    private static String defaultSqlResourcePattern = "classpath*:sql/*.sql";

    private static String defaultScriptCharset = "UTF-8";

    public void executeSqlIfNotExistDb(Connection connection,String database){
        executeSqlIfNotExistDb(connection,database,defaultSqlResourcePattern,defaultScriptCharset);
    }

    public void executeSqlIfNotExistDb(Connection connection,String database,String sqlSourcePatten,String scriptCharset){

    }
}
