package com.wisdom.utils;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;
public class ProjectUtils {
	@Value("${config.published_file_path}")
	private static String published_file_path;

	private ProjectUtils() {
	}
	
	public static String urlEnodeUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
	
	public static String FormetFileSize(long fileS) {//转换文件大小
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = NumberFormat.round((double) fileS,2) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = NumberFormat.round((double) fileS / 1024,2) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = NumberFormat.round((double) fileS / 1048576,2) + "M";
        } else {
            fileSizeString = NumberFormat.round((double) fileS / 1073741824,2) + "G";
        }
        return fileSizeString;

    }

	/**
	 * 装位数不够totalBit的补0
	 * @param value
	 * @param totalBit
	 * @return
	 */
	public static String fillZero(int value, int totalBit) {
		return fillZero(String.valueOf(value), totalBit);
	}
	
	public static String fillZero(String value, int totalBit) {
		int length = value.length();
		int fillLength = totalBit - length;
		if(fillLength < 0) throw new MyRuntimeException("com.project.framework.util.ProjectUtils - fillZero: value.length()>totalBit!");
		String returnValue = "";
		for(int i=0; i<fillLength; i++) {
			returnValue += "0";
		}
		returnValue += value;
		return returnValue;
	}
	
	/**
	 * 保存上传文件
	 * @param input File : 上传文件
	 * @return 实际文件编号
	 */	
	public static boolean writeAttachFile(String ID, File input,String filePath) {
		try {
			return writeAttachFile(ID, new FileInputStream(input),"");
		}catch(Exception e) {
			throw new MyRuntimeException();
		}
	}
	
	
	/**
	 * 保存上传文件
	 * @param input : 上传流
	 * @return 实际文件编号
	 */
	public static boolean writeAttachFile(String fileName, InputStream input,String filePath) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		boolean flag=false;
		try {
			File existsDir = new File(filePath); 
			if(!existsDir.exists()){
				existsDir.mkdirs();
			}
			bis = new BufferedInputStream(input);
			bos = new BufferedOutputStream(new FileOutputStream(filePath+File.separator+fileName));
			int n = -1;
			while((n=bis.read()) != -1) bos.write(n);
			flag=true;
		} catch(Exception e) {
			throw new MyRuntimeException(e);
		} finally {
			try {
				if(bis != null) bis.close();
				if(bos != null) bos.close();
			}catch(Exception ee) {
				throw new MyRuntimeException(ee);
			}
		}
		return flag;
	}
	
	

	/**
	 * 根据fileId删除对应文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteAttachFile(String filePath) {
		File file = new File(filePath);
		return file.delete();
	}
	
	/**
	 * 装字符串转换成字节流
	 * @param text
	 * @return
	 */
	public static ByteArrayInputStream toByteArrayInputStream(String text) {
		return new ByteArrayInputStream(text.getBytes());
	}
	public static void copy(File input, File output) throws Exception {
		copy(new FileInputStream(input), new FileOutputStream(output));
	}
	
	public static void copy(InputStream input, File output) throws Exception {
		copy(input, new FileOutputStream(output));
	}
	
	public static void copy(File input, OutputStream output) throws Exception {
		copy(new FileInputStream(input), output);
	}
	
	/**
	 * 文件复制
	 * @param is
	 * @param os
	 */
	public static void copy(InputStream is, OutputStream os) throws Exception {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(os);
			int n = -1;
			while((n=bis.read()) != -1) bos.write(n);
		}finally {
			if(bis!=null) bis.close();
			if(bos!=null) bos.close();
		}
	}
	
	public static void writString(HttpServletResponse response,String returnStr){
		response.setContentType("text/html;charset=UTF-8");
		try{
			PrintWriter pw = response.getWriter();
			pw.write(returnStr);
			pw.flush();
			pw.close();
		}catch(Exception e){
			//日记
			throw new MyRuntimeException(e);
		}
	}
	
	/**
	 * 下载附件
	 * @param response
	 * @param fileName : 附件导出名称
	 * @param newName : 存盘文件名称
	 */
	public static void download(HttpServletResponse response,String fileName,String newName) {
		try {
			response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(fileName, "UTF-8"));
			readAttachFile(response.getOutputStream(), newName);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取文件
	 * @param output : 输出流
	 * @param fileName : 附件存入磁盘中的名称
	 */
	public static void readAttachFile(OutputStream output, String fileName) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(new File(published_file_path+File.separator+fileName)));
			bos = new BufferedOutputStream(output);
			int n = -1;
			while((n=bis.read()) != -1) bos.write(n);
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bis != null) bis.close();
				if(bos != null) bos.close();
			}catch(Exception ee) {
				ee.printStackTrace();
			}
		}
	}
	
    /**  
     * 返回指定年度的所有周。String[][]中包含的是String[2]对象<br>  
     * string[0]本周的开始日期,string[1]是本周的结束日期。<br>  
     * 日期的格式为yyyy-MM-dd。<br>  
     * 每年的第一个周，必须包含星期一且是完整的七天。<br>  
     * 例如：2009年的第一个周开始日期为2009-01-05，结束日期为2009-01-11。 <br>  
     * 星期一在哪一年，那么包含这个星期的周就是哪一年的周。<br>  
     * 例如：2008-12-29是星期一，2009-01-04是星期日，哪么这个周就是2008年度的最后一个周。<br>  
     * @param year 格式 yyyy  ，必须大于1900年度 小于9999年   
     * @return   
     */  
    public static String[][] getWeeksDateByYear(int year) {
    	Calendar cal = Calendar.getInstance();
    	year = year > 0 ? year : cal.get(Calendar.YEAR);
        cal.setFirstDayOfWeek(Calendar.MONDAY); 				//设置每周的第一天为星期一   
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);			//每周从周一开始      
        cal.setMinimalDaysInFirstWeek(7);  						//设置每周最少为7天        
        cal.set(Calendar.YEAR, year);   
        cal.set(Calendar.WEEK_OF_YEAR, 53);
        int weekcount = cal.get(Calendar.YEAR)==year ? 53 : 52;
        String[][] datearr = new String[weekcount][2];
        for(int i=0; i<weekcount; i++) {
        	cal.setFirstDayOfWeek(Calendar.MONDAY); 				//设置每周的第一天为星期一   
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);			//每周从周一开始      
            cal.setMinimalDaysInFirstWeek(7);  						//设置每周最少为7天        
            cal.set(Calendar.YEAR, year);   
        	cal.set(Calendar.WEEK_OF_YEAR, i+1);
        	int y = cal.get(Calendar.YEAR);
        	int m = cal.get(Calendar.MONTH)+1;
        	int d = cal.get(Calendar.DATE);
        	datearr[i][0] = y+"-"+(m<10?("0"+m):m+"")+"-"+(d<10?("0"+d):d+"");
        	cal.setTimeInMillis(cal.getTimeInMillis()+6*24*60*60*1000);
        	y = cal.get(Calendar.YEAR);
        	m = cal.get(Calendar.MONTH)+1;
        	d = cal.get(Calendar.DATE);
        	datearr[i][1] = y+"-"+(m<10?("0"+m):m+"")+"-"+(d<10?("0"+d):d+"");
        }
        return datearr;
    }
	
   /*   返回某年某月多少天
    *   从0开始，0代表一月
    *   调用：getLastDayOfMonth(2102,7) 返回 31
    */
    public static int getLastDayOfMonth(int year, int month) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, year);     
        cal.set(Calendar.MONTH, month);     
       return cal.getActualMaximum(Calendar.DATE);
       
    }   
    /**
	 * 
	 * 功能描述：将字符串转换为指定长度输出,高位补0
	 * @param s 转入的需要转换的的字符串
	 * @param length 转换后字符串的长度
	 * @return 转换后的字符串
	 */
	public static String stringToLength(String s,int length){
		String temp="";
		if(s==null){
			for(int i=0;i<length;i++){
				temp+=0;				
			}
			s=temp;
		}
		else if(s.length()<length){
			
			for(int i=0;i<length-s.length();i++){
				temp+=0;				
			}
			s=temp+s;		
		}	
		
		return s;
	}
    
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		int hour = c.get(Calendar.HOUR_OF_DAY); 
		int minute = c.get(Calendar.MINUTE); 
		int second = c.get(Calendar.SECOND); 
		System.out.println(year + "/" + month + "/" + date + " " +hour + ":" +minute + ":" + second); 
		/*
		File existsDir = new File("d:/lsems/FileSpace"); 
		System.out.println(existsDir.isDirectory());
		if(!existsDir.exists()){
			existsDir.mkdirs();
		}*/
		
	}
}
