package com.wisdom;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args)throws Exception{
        JSONArray arr=JSON.parseArray(JSON.toJSONString(new Test().searchProv()));
        System.out.println(arr.toString());
    }

    public Connection getMySqlConnection(String url, String userName, String pwd)throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, userName, pwd);
    }

    public List searchProv()throws Exception{
        String sql="select code as value,name as text from provinces a order by a.code ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List arr=new ArrayList();
        try{
            String url="jdbc:mysql://120.79.222.9:3306/wisdom?relaxAutoCommit=true&zeroDateTimeBehavior=convertToNull&treatTinyAsBoolean=false";
            String userName="root";
            String pwd="5lthqlkzVRK6Mk75";
            conn = this.getMySqlConnection(url, userName, pwd);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Map map=new HashMap();
                map.put("value",rs.getString("value"));
                map.put("text",rs.getString("text"));
                List citysList=searchCitys(rs.getString("value"));
                if(citysList.size()>0){
                    map.put("children",citysList);
                }
                arr.add(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return arr;
    }

    public List searchCitys(String code)throws Exception{
        String sql="select code as value,name as text from citys a where a.provincesId='"+code+"' order by a.code ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List arr=new ArrayList();
        try{
            String url="jdbc:mysql://120.79.222.9:3306/wisdom?relaxAutoCommit=true&zeroDateTimeBehavior=convertToNull&treatTinyAsBoolean=false";
            String userName="root";
            String pwd="5lthqlkzVRK6Mk75";
            conn = this.getMySqlConnection(url, userName, pwd);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Map map=new HashMap();
                map.put("value",rs.getString("value"));
                map.put("text",rs.getString("text"));
                List citysList=searchAreas(rs.getString("value"));
                if(citysList.size()>0){
                    map.put("children",citysList);
                }
                arr.add(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return arr;
    }

    public List searchAreas(String code)throws Exception{
        String sql="select code as value,name as text from areas a where a.cityId='"+code+"' order by a.code ";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List arr=new ArrayList();
        try{
            String url="jdbc:mysql://120.79.222.9:3306/wisdom?relaxAutoCommit=true&zeroDateTimeBehavior=convertToNull&treatTinyAsBoolean=false";
            String userName="root";
            String pwd="5lthqlkzVRK6Mk75";
            conn = this.getMySqlConnection(url, userName, pwd);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Map map=new HashMap();
                map.put("value",rs.getString("value"));
                map.put("text",rs.getString("text"));
                arr.add(map);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return arr;
    }



}
