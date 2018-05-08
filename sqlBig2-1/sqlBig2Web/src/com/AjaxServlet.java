package com;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

@WebServlet(name = "AjaxServlet")
public class AjaxServlet extends HttpServlet {
    private Sqlbig2 sqlbig = new Sqlbig2();

    private JSONObject getInfos(int firstN,int page) {
        List<String> all = sqlbig.getAll();
        JSONObject jsonObject = new JSONObject();
        int endN = all.size()-page*52>firstN+52?firstN+52:all.size()-page*52;
        System.out.println("firstN:"+firstN);
        System.out.println("endN:"+endN);
        for(int i=firstN;i<firstN+endN;i+=4) {
            List<String> list=new ArrayList<>();
            list.add(all.get(i));
            list.add(all.get(i+1));
            list.add(all.get(i+2));
            list.add(all.get(i+3));
            jsonObject.put(i/4,list);
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

    private JSONObject getAllInfos() {
        List<String> all = sqlbig.getAll();
        JSONObject jsonObject = new JSONObject();
        System.out.println(all.size());
        for(int i=0;i<all.size();i+=4) {
            List<String> list=new ArrayList<>();
            list.add(all.get(i));
            list.add(all.get(i+1));
            list.add(all.get(i+2));
            list.add(all.get(i+3));
            jsonObject.put(i/4,list);
        }
        System.out.println(jsonObject);
        return jsonObject;
    }
    private static final long serialVersionUID = 1L;

    public AjaxServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        writer.println("GET " + request.getRequestURL() + " "+ request.getQueryString());

        Map<String, String[]> params = request.getParameterMap();
        String queryString = "";
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        // 去掉最后一个空格sqlbig
        queryString = queryString.substring(0, queryString.length() - 1);
//        writer.println("GET " + request.getRequestURL() + " " + queryString);
//        System.out.println(queryString);

//        response.setCharacterEncoding("UTF-8");只对post有效
//        System.out.println(request.getRequestURL());
        System.out.println(queryString);
        String[] str = queryString.split("\\=");
        response.setHeader("Content-type","text/html;charset=UTF-8");
        response.setContentType("text/html;charset=UTF-8");
//      下一句必须写在前两句后面
        PrintWriter writer = response.getWriter();
        System.out.println(str[1]);
        if(str[1].equals("all")) {
            writer.write(getAllInfos().toString());
            System.out.println(getAllInfos().toString());
        }else {
            int page = Integer.parseInt(str[1])-1;
            System.out.println(page);
            writer.write(getInfos(page*52,page).toString());
            System.out.println(getInfos(page*52,page).toString());
        }
    }
}
