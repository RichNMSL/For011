package com.neusoft;

import com.util.DButil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NeuDao {
    private static DataSource dataSource = DButil.getDataSource();


    public void insertTable(String gongdan,String message) throws SQLException {

        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "insert into  tu_neusoft_gd (gongdan,message)values(?,?) ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gongdan);
            preparedStatement.setString(2, message);


            // 执行预编译好的SQL语句
            preparedStatement.executeUpdate();

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }

    }
}
