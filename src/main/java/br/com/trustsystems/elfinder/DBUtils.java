package br.com.trustsystems.elfinder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {

    public List<String> search(String sql) {
        List<String> result = new ArrayList<>();
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://http://192.168.200.103:3306/mkcops";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "12qw!@QW";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句

            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);


            String job;
            while (rs.next()) {
                //获取stuname这列数据
                job = rs.getString("path");
                result.add(job);

            }
            rs.close();
            con.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("数据库数据成功获取！！");
        }
        return result;
    }
}
