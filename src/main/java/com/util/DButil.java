package com.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DButil {

    /**
     * 数据库连接对象
     */
    private static DataSource dataSource;

    /*
     获取数据库连接池对象
     */
    static {

        try {
            // 获取加载配置文件的对象
            Properties properties = new Properties();
            // 获取类的类加载器
            ClassLoader classLoader = DButil.class.getClassLoader();
            // 获取druid-1.0.9.properties配置文件资源输入流
            InputStream resourceAsStream = classLoader.getResourceAsStream("druid-1.0.9.properties");
            // 加载配置文件
            properties.load(resourceAsStream);
            // 获取连接池对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获取数据库连接对象
     */
    public static Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    /**
     * 归还连接
     * @param t 要被归还到熟即可连接池对象的数据库连接对象
     * @param <T> 数据库连接对象的类型
     */
    public static <T> void releaseResources (T t){
        if(t != null){
            try {
                // 利用反射，获取class对象
                Class<?> aClass = t.getClass();
                // 获取class对象中的方法对象
                Method close = aClass.getMethod("close");
                // 执行方法
                close.invoke(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        DataSource dataSource = DButil.getDataSource();
        Connection connection = dataSource.getConnection();
    }
}
