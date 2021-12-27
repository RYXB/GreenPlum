package api;

import Entity.TbStructure;

import java.sql.*;
import java.util.*;

public class Connect_Greenplum {
    //三大核心接口
    private static Connection conn = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    //连接数据库
    public static Connection connectGreenplum() throws ClassNotFoundException, SQLException {
        // URL
        String url = "jdbc:pivotal:greenplum://172.16.18.10:5432;DatabaseName=sjck_fb";
        // 数据库用户名
        String username = "etl_gp";
        // 数据库密码
        String password = "gp123";
        // 加载驱动
        Class.forName("com.pivotal.jdbc.GreenplumDriver");
        // 获取连接
        conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    //关闭数据库连接
    public static  void closeConnection(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(pstmt!=null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //查询方法
    public static ResultSet query(String sql) throws SQLException, ClassNotFoundException {
        Connection conn = connectGreenplum();
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        return rs;
    }

    //删除表
    public static Integer dropTable(String tableName) throws SQLException, ClassNotFoundException {
        conn = connectGreenplum();
        String sql = "DROP TABLE IF EXISTS " + tableName + ";";
        pstmt = conn.prepareStatement(sql);
        int rs = pstmt.executeUpdate();
        return rs;
    }

    //删除外部表
    public static Integer dropExternalTable(String tableName) throws SQLException, ClassNotFoundException {
        conn = connectGreenplum();
        String sql = "DROP EXTERNAL TABLE if EXISTS "+tableName+";";
        pstmt = conn.prepareStatement(sql);
        Integer rs = pstmt.executeUpdate();
        return rs;
    }

    //创建表
    public static Integer createTable(String tableName,String columnInfo,String distributedKey) throws SQLException, ClassNotFoundException {
        conn = connectGreenplum();
        TbStructure tbStructure = new TbStructure();
        String sql = "CREATE TABLE " + tableName +" ("+columnInfo+")\n" +
                "WITH (appendonly="+tbStructure.isAppendonly()+", " +
                "compresstype="+tbStructure.getCompresstype()+",\n" +
                "compresslevel="+tbStructure.getCompresslevel()+") " +
                "DISTRIBUTED BY ("+distributedKey+");";
        pstmt = conn.prepareStatement(sql);
        Integer rs = pstmt.executeUpdate();
        return rs;
    }

    //输出
    public static void output(ResultSet rs) throws SQLException {
        while(rs.next())
        {
            System.out.println(
                    "id："+rs.getString("id")+
                            " 日期:"+rs.getString("date")+
                            " 价格:"+rs.getString("amt"));
        }
    }
}
