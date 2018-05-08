package com;
import javax.servlet.ServletContextAttributeEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class Sqlbig2 {
    private static Statement stat=null;
    public static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sqlBig";
        String username = "root";
        String password = "kkxxdgmyt67LIUQIONG";
        Connection conn = null;
        try{
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,username,password);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void add(String username,String name,int age,String teleno) {
        Connection conn = getConn();
        String sql = "INSERT INTO sqlbig VALUES ('"+username+"','"+name+"','"+age+"','"+teleno+"');";
        try {
            stat = conn.createStatement();
            stat.execute(sql);
            System.out.println("add successfully");
//            pstmt.close();
//            conn.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void del(String username) {
        Connection conn = getConn();
        String sql = "DELETE FROM sqlbig where username = '"+username+"';";
        PreparedStatement pstmt;
        try{
            stat = conn.createStatement();
            stat.execute(sql);
            System.out.println("delete successfully");
//            pstmt.close();
//            conn.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static void print(){
        Connection conn = getConn();
        String sql = "SELECT * FROM sqlbig";
        PreparedStatement pstmt;
        try{
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
//            pstmt.close();
//            conn.close();
            System.out.println("table:");
            while(rs.next()) {
                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<String> getAll() {
        Connection conn = getConn();
        String sql = "SELECT * FROM sqlbig";
        PreparedStatement pstmt;
        try {
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<String> all = new ArrayList<>();
            while(rs.next()) {
                all.add(rs.getString("username"));
                all.add(rs.getString("name"));
                all.add(rs.getString("age"));
                all.add(rs.getString("teleno"));
            }
            System.out.println(rs);
            return all;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String args[]) {

    }
}

//public class Sqlbig2 {
//    //mysql驱动包名
//    private static final String DRIVER_NAME="com.mysql.jdbc.Driver";
//    //数据库连接地址
//    private static final String URL = "jdbc:mysql://localhost:3306/txt";
//    //用户名
//    private static final String USER_NAME = "root";
//    //密码
//    private static final String PASSWORD = "kkxxdgmyt67LIUQIONG";
//
//    public static void main(String args[]){
//        Connection conn = null;
//        try{
//            //加载mysql的驱动
//            Class.forName(DRIVER_NAME);
//            //获取数据库连接
//            conn=DriverManager.getConnection(URL,USER_NAME,PASSWORD);
//            //mysql查询语句
//            String sql = "SELECT teachersName FROM teachers";
//            PreparedStatement prst = conn.prepareStatement(sql);
//            //结果集
//            ResultSet rs = prst.executeQuery();
//            while(rs.next()){
//                System.out.println("用户名："+ rs.getString("teachersName"));
//            }
//            rs.close();
//            prst.close();
//        }catch(Exception e) {
//            e.printStackTrace();
//        }finally {
//            if(conn!=null) {
//                try{
//                    conn.close();
//                }catch(SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
