package com.dao;

import com.util.DButil;
import com.vo.Accused;
import com.vo.Judgment;
import com.vo.Lawyer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllDao {


    private static DataSource dataSource = DButil.getDataSource();


    public static void insertxuhui(String task_name,String source_datasource_name,String target_datasource_name,String  status,String description,String period,String dispatch_interval
                                   ) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "INSERT INTO tu_demo_xuhui (task_name,source_datasource_name,target_datasource_name,status,description,period,dispatch_interval)  VALUES (?,?,?,?,?,?,?);";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 给?赋值
            preparedStatement.setString(1, task_name);
            preparedStatement.setString(2, source_datasource_name);
            preparedStatement.setString(3, target_datasource_name);
            preparedStatement.setString(4, status);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, period);
            preparedStatement.setString(7, dispatch_interval);

            // 执行预编译好的SQL语句
            preparedStatement.executeUpdate();

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(task_name);
            DButil.releaseResources(connection);

        } finally {

        }
    }


    public static void insertJudgment(Judgment judgment) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "INSERT INTO tu_demo_judgment_2017 (zipName,fileName,title,branch,code,is_easy)  VALUES (?,?,?,?,?,?);";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 给?赋值
            preparedStatement.setString(1, judgment.getZipName());
            preparedStatement.setString(2, judgment.getFileName());
            preparedStatement.setString(3, judgment.getTitle());
            preparedStatement.setString(4, judgment.getBranch());
            preparedStatement.setString(5, judgment.getCode());
            preparedStatement.setString(6, judgment.getIs_easy());

            // 执行预编译好的SQL语句
            preparedStatement.executeUpdate();

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(judgment.getFileName());
            DButil.releaseResources(connection);

        } finally {

        }
    }

    public static void insertAccused(Accused accused) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "INSERT INTO tu_demo_accused_2017 (code,name ,IS_LAWYER,IS_ENTRUST,lawyerName)  VALUES (?,?,?,?,?);";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 给?赋值
            preparedStatement.setString(1, accused.getCode());
            preparedStatement.setString(2, accused.getName());
            preparedStatement.setString(3, accused.getIsLawyer());
            preparedStatement.setString(4, accused.getIsEntrust());
            preparedStatement.setString(5, accused.getLawyerName());

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
            System.out.println(accused.getCode());

        } finally {

        }
    }

    public static void insertLawyer(Lawyer lawyer) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "INSERT INTO tu_demo_Lawyer(code,name,is_entrust)  VALUES (?,?,?);";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 给?赋值
            preparedStatement.setString(1, lawyer.getCode());
            preparedStatement.setString(2, lawyer.getName());
            preparedStatement.setString(3, lawyer.getIs_entrust());


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
            System.out.println(lawyer.getCode());
        } finally {

        }
    }

    public static void main(String[] args) {
        String a = "上海市虹口区人民检察院以沪虹检二部刑诉〔2018〕47号起诉书指控被告人崔俊、巩某某、耿某某、董某某犯诈骗罪，于2018年12月3日向本院提起公诉。本院依法组成合议庭，公开开庭审理了本案。上海市虹口区人民检察院指派检察员曹某出庭支持公诉，被告人崔俊及其辩护人吴刚、被告人巩某某及其辩护人吕正崧、被告人耿某某及其辩护人冯坤、被告人董某某及上海市虹口区法律援助中心为其指定的辩护人陈韵均到庭参加诉讼。现已审理终结。</div>";
        System.out.println(a.contains("及其"));

    }

    public List<Judgment> queryLawyers() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "SELECT b.code,a.zipName,a.fileName ,B.lawyerName,b.id from tu_demo_judgment_2017 a,tu_demo_accused_2017 b where a.`code`=b.`CODE`\n" +
                    "and b.IS_LAWYER='Y' and b.IS_ENTRUST ='Y'  and a.`code` in (\n" +
                    "select  code from tu_demo_accused_2017 c GROUP BY code having count(1)=1 )";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setCode(result.getString("code"));
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setTitle(result.getString("lawyerName"));
                judgment.setBranch(result.getString("id"));
                list.add(judgment);
            }

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;

    }
    public List<Judgment> queryLawyersss() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "SELECT b.code,a.zipName,a.fileName ,B.lawyerName,b.id from tu_demo_judgment_2017 a,tu_demo_accused_2017 b where a.`code`=b.`CODE`\n" +
                    "and trim(b.name) ='被告人'";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setCode(result.getString("code"));
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setTitle(result.getString("lawyerName"));
                judgment.setBranch(result.getString("id"));
                list.add(judgment);
            }

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;

    }


    public List<Judgment> queryLawyersssaaa() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "select  a.code ,zipName,fileName  ,id  from tu_demo_judgment_2017_log_new  a\n" +
                    "where branch IS NOT NULL and title NOT LIKE '人民法院认为%' and is_easy is null ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setCode(result.getString("code"));
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setBranch(result.getString("id"));
                list.add(judgment);
            }

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;

    }

    public List<Judgment> queryLawyers_2() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "SELECT b.code,a.zipName,a.fileName ,B.lawyerName,b.id from tu_demo_judgment_2017 a,tu_demo_accused_2017 b where a.`code`=b.`CODE`\n" +
                    "and b.IS_LAWYER='Y' and b.IS_ENTRUST ='Y' and a.`code` in (\n" +
                    "select  code from tu_demo_accused_2017 c WHERE c.IS_ENTRUST ='Y' and c.IS_LAWYER='Y' GROUP BY code having count(1)=1 )"+
                    "AND a.code  in (\n" +
                    "select CODE  from tu_demo_accused_2017 where IS_ENTRUST ='N'\n" +
                    ")";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setCode(result.getString("code"));
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setTitle(result.getString("lawyerName"));
                judgment.setBranch(result.getString("id"));
                list.add(judgment);
            }

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;

    }

    public List<Judgment> queryLawyers_new() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = " SELECT b.code,a.zipName,a.fileName ,B.lawyerName,b.id from tu_demo_judgment_2017 a,tu_demo_accused_2017 b where a.`code`=b.`CODE`\n" +
                    "and b.IS_LAWYER='Y' and b.IS_ENTRUST ='Y' and a.`code` in (\n" +
                    "select  code from tu_demo_accused_2017 c WHERE c.IS_ENTRUST='Y' and c.IS_LAWYER='Y' GROUP BY code having count(1)>1 ) ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setCode(result.getString("code"));
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setTitle(result.getString("lawyerName"));
                judgment.setBranch(result.getString("id"));
                list.add(judgment);
            }

            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;
    }

    public void updateByid(String id) throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_accused_2017 a set a.is_lawyer='Y',IS_ENTRUST='N' where id=?  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);


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

    public void updateByidN(String id) throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_accused_2017_log a set a.IS_ENTRUST='N' where id=?  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);


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


    public void updateByidY(String id) throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_accused_2017_log a set a.IS_ENTRUST='Y' where id=?  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);


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

    public void updateByidYAA(String id) throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "UPDATE tu_demo_judgment_2017_log_new SET is_easy='3' WHERE ID=?  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);


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

    public void updateByidLoss(String id) throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_accused_2017 a set a.IS_ENTRUST='wait' where id=?  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);


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

    public List<Judgment> queryNeedFix() throws SQLException {

        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "select distinct  b.code,b.zipName,b.fileName,a.lawyerName,A.ID from tu_demo_accused_2017_log a LEFT JOIN tu_demo_judgment_2017_log B  ON  A.`CODE`=B.`code`\n" +
                    "   WHERE  a.IS_ENTRUST='wait'   ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setCode(result.getString("code"));
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setTitle(result.getString("lawyerName").trim());
                judgment.setBranch(result.getString("id"));

                list.add(judgment);
            }


            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;

    }

    public int queryInDB(String code) throws SQLException {
        Connection connection = dataSource.getConnection();
        int count = 0;
        try {
            // 预定义定义SQL语句
            String sql = "select count(1) as con from tu_demo_accused_2017_log a where  a.IS_ENTRUST ='N' and  a.IS_LAWYER ='Y' and  a.`CODE`=?  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);

            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                count = result.getInt("con");
            }


            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return count;

    }

    public void updatebyCode(String code) throws SQLException {

        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_accused_2017_log a set a.IS_ENTRUST='Y' where code=? and a.IS_ENTRUST='wait' ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);


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

    public void updateyqtx(String code) throws SQLException {

        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_accused a set a.yqtx='3' where a.id=? ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);


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
    public List<Judgment> queryNeedFixCode() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "select distinct b.code,b.zipName,b.fileName from tu_demo_accused_step a LEFT JOIN tu_demo_judgment_log B  ON  A.`CODE`=B.`code` \n" +
                    "WHERE  a.IS_ENTRUST='wait'  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setCode(result.getString("code"));
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));

                list.add(judgment);
            }


            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;
    }


    public List<Judgment> queryNeedFixEasy() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> list = new ArrayList<Judgment>();

        try {
            // 预定义定义SQL语句
            String sql = "select a.fileName,a.zipName from tu_demo_judgment_log a,  tu_demo_judgment_log b  where a.`CODE`=b.`code` and b.title like '%嘉定%'; ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));

                list.add(judgment);
            }


            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }
        return list;
    }

    public void updateBody(String code, String message) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_judgment_log a set a.body=? where  a.code=? ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message);
            preparedStatement.setString(2, code);


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

    public void updateEasyN(String title) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_judgment_log a set a.is_easy='N' where ID=? ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);


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

    public void updateEasyY(String title) throws SQLException {
        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = "update tu_demo_judgment_log a set a.is_easy='Y' where ID=? ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);


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


    public List queryNeedBaoShan() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> codeList = new ArrayList<Judgment>();
        try {
            // 预定义定义SQL语句
            String sql = "select b.fileName,b.`code`,b.zipName,a.`NAME`,a.id from tu_demo_accused_2017 a ,tu_demo_judgment_2017 b where a.`CODE`=b.`code`   ";
            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setCode(result.getString("code"));
                 judgment.setTitle(result.getString("name").trim());
                judgment.setBranch(result.getString("id"));

                codeList.add(judgment);
            }


            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }

        return codeList;
    }



    public void updatebyCodeNew(String code) throws SQLException {

        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = " update tu_demo_judgment a set a.is_easy='1' where code=?   ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, code);



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

    public List queryNeedNMSL() throws SQLException {
        Connection connection = dataSource.getConnection();
        List<Judgment> codeList = new ArrayList<Judgment>();
        try {
            // 预定义定义SQL语句
            String sql = " select distinct a.CODE,b.fileName,b.zipName,a.`NAME`,a.id from tu_demo_accused_step a,tu_demo_judgment_log B  where\n" +
                    "a.code=b.code and a.code in(select code from nmsl)\n" +
                    "and a.IS_LAWYER IS NULL";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            // 执行预编译好的SQL语句
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Judgment judgment = new Judgment();
                judgment.setZipName(result.getString("zipName"));
                judgment.setFileName(result.getString("fileName"));
                judgment.setCode(result.getString("code"));
                judgment.setBranch(result.getString("name"));
                judgment.setTitle(result.getString("id"));

                codeList.add(judgment);
            }


            // 释放资源：PreparedStatement
            DButil.releaseResources(preparedStatement);

            // 归还连接
            DButil.releaseResources(connection);

            // 释放资源：数据库连接池

        } catch (Exception e) {
            e.printStackTrace();
            DButil.releaseResources(connection);
        }

        return codeList;
    }

    public void updateInfor(String branch, String substring) throws SQLException {

        Connection connection = dataSource.getConnection();

        try {
            // 预定义定义SQL语句
            String sql = " UPDATE tu_demo_accused_step A SET A.IS_LAWYER='Y',A.IS_ENTRUST='N',A.lawyerName= ? where id=?  ";

            // 获取执行预定义SQL语句对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, substring);
            preparedStatement.setString(2, branch);


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