package com;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    private String message;
    private Sqlbig2 sqlBig = new Sqlbig2();
    public void init() throws ServletException {
//        message = "table";
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置响应内容类型
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //获取url
        String req = request.getQueryString();
        String[] str = req.split("\\&");
        System.out.println(str.length);

        if(str.length>2) {

            String username = new String(request.getParameter("username"));
            String name =  new String(request.getParameter("name"));
            int age = new Integer(request.getParameter("age"));
            String tel = new String(request.getParameter("tel"));
            //往表格添加数据
            sqlBig.add(username,name,age,tel);
            out.println("<style>" +
                    "   h1 {" +
                    "         text-align: center;" +
                    "       }" +
                    "</style>");
            out.println("<h1>add successfully!</h1>");
            out.println("<script>" +
                    "(function(){" +
                    "   var time = setTimeout(function(){" +
                    "       window.location.href='index.html';" +
                    "   },1000);" +
                    "return function(){" +
                    "   clearTimeout(time);" +
                    "}})();" +
                    "</script>");
//            out.println("<p>"+"username: "+username+"</p>");
//            out.println("<p>"+"name: "+name+"</p>");
//            out.println("<p>"+"age: "+age+"</p>");
//            out.println("<p>"+"tel: "+tel+"</p>");
            //打印表格
//            sqlBig.print();
        }else {
            String username = new String(request.getParameter("username"));
            //往表格中删除数据
            sqlBig.del(username);
//            out.println("<h1>"+message+"</h1>");
            out.println("<style>" +
                    "   h1 {" +
                    "         text-align: center;" +
                    "       }" +
                    "</style>");
            out.println("<h1>delete successfully!</h1>");
            out.println("<script>" +
                    "(function(){" +
                    "   var time = setTimeout(function(){" +
                    "       window.location.href='index.html';" +
                    "   },1000);" +
                    "return function(){" +
                    "   clearTimeout(time);" +
                    "}})();" +
                    "</script>");
            //打印表格
//            sqlBig.print();
        }

        //发送数据到前端
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
    }

    //处理Post方法请求的方法
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //必须要的：运行doPost
        doPost(request,response);
    }
}
