package com.viewstar.jsdatamonitor.util;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import org.h2.jdbcx.JdbcConnectionPool;

public class H2JdbcUtil {
	 /**
     * H2数据库自带的连接池
     */
    private static JdbcConnectionPool cp = null;
    
    static{
        try {
            //加载src目录下的h2config.properties
            InputStream in = H2JdbcUtil.class.getClassLoader().getResourceAsStream("h2config.properties");
            Properties prop = new Properties();
            prop.load(in);
            //创建数据库连接池
            cp = JdbcConnectionPool.create(prop.getProperty("JDBC_URL"), prop.getProperty("USER"), prop.getProperty("PASSWORD"));
        } catch (Exception e) {
            System.out.println("连接池初始化异常");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws Exception{
        return cp.getConnection();
    }

    public static JdbcConnectionPool getCp() {
        return cp;
    }
}
