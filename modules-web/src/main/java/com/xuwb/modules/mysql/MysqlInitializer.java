package com.xuwb.modules.mysql;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class MysqlInitializer {

    private static String defaultSqlResourcePattern = "classpath*:sql/*.sql";

    private static String defaultScriptCharset = "UTF-8";

    public void executeSqlIfNotExistDb(Connection connection,String database) throws SQLException {
        executeSqlIfNotExistDb(connection,database,defaultSqlResourcePattern,defaultScriptCharset);
    }

    public void executeSqlIfNotExistDb(Connection connection,String database,String sqlSourcePatten,String scriptCharset) throws SQLException {
        //判断数据库是否存在
        if (!isDbExists(connection,database)){
            //执行sql目录下的sql语句
        }
    }

    private static boolean isDbExists(Connection connection, String database) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "SELECT COUNT(*) FROM information_schema.schemata WHERE schema_name=\"" + database + "\"";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt(1) != 0;
        }
        return false;
    }
}
