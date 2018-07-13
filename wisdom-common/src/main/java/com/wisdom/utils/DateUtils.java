 package com.wisdom.utils;
 
 import com.google.common.base.Preconditions;
 import java.text.SimpleDateFormat;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.TimeZone;
 import org.apache.commons.lang3.StringUtils;
 
 public class DateUtils
 {
   public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
   public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
   public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
   private static final TimeZone TIMEZONE = TimeZone.getTimeZone("GMT+8");
 
   public static Date addDay(Date date, int day)
   {
     Preconditions.checkArgument(date != null, "Param date must be not null");
     Preconditions.checkArgument(day > 0, "Param day must gt 0");
     Calendar cal = Calendar.getInstance();
     cal.setTime(date);
     cal.add(5, day);
     return cal.getTime();
   }
 
   public static Date addHour(Date date, int hour) {
     Preconditions.checkArgument(date != null, "Param date must be not null");
     Preconditions.checkArgument(hour > 0, "Param hour must gt 0");
     Calendar cal = Calendar.getInstance();
     cal.setTime(date);
     cal.add(11, hour);
    return cal.getTime();
   }
 
   public static String currentTimeMillis()
   {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    return sdf.format(new Date());
   }
 
   public static String currentTimeSecond()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     return sdf.format(new Date());
   }
 
   public static String currentTimeDay()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     return sdf.format(new Date());
   }
 
   public static String format(Date date, String format) {
     Preconditions.checkArgument(date != null, "Param date must be not null");
     Preconditions.checkArgument(StringUtils.isNotBlank(format), "Param format must be not null or empty");
     SimpleDateFormat sdf = new SimpleDateFormat(format);
     return sdf.format(date);
   }
 
   public static int compare(Date date1, Date date2)
   {
    Preconditions.checkArgument(date1 != null, "Param date1 must be not null");
   Preconditions.checkArgument(date2 != null, "Param date2 must be not null");
 
     if (date1.after(date2))
       return 1;
     if (date1.before(date2)) {
      return -1;
     }
    return 0;
   }
 
   public static long format2Long(Date date)
   {
     return format2Long(date, false);
   }
 
   public static long format2Long(Date date, boolean hasMillisecond)
   {
    Calendar calendar = Calendar.getInstance(TIMEZONE);
     calendar.setTime(date);
   String year = String.format("%04d", new Object[] { Integer.valueOf(calendar.get(1)) });
  String month = String.format("%02d", new Object[] { Integer.valueOf(calendar.get(2) + 1) });
    String day = String.format("%02d", new Object[] { Integer.valueOf(calendar.get(5)) });
    String hour = String.format("%02d", new Object[] { Integer.valueOf(calendar.get(11)) });
    String minute = String.format("%02d", new Object[] { Integer.valueOf(calendar.get(12)) });
    String second = String.format("%02d", new Object[] { Integer.valueOf(calendar.get(13)) });
     String str = year + month + day + hour + minute + second;
     if (hasMillisecond) {
      String millisecond = String.format("%03d", new Object[] { Integer.valueOf(calendar.get(14)) });
       str = str.concat(millisecond);
     }
    return Long.parseLong(str);
   }
 
   public static int format2Int(Date date)
   {
    Calendar calendar = Calendar.getInstance(TIMEZONE);
    calendar.setTime(date);
    String year = String.format("%04d", new Object[] { Integer.valueOf(calendar.get(1)) });
   String month = String.format("%02d", new Object[] { Integer.valueOf(calendar.get(2) + 1) });
    String day = String.format("%02d", new Object[] { Integer.valueOf(calendar.get(5)) });
   return Integer.parseInt(year + month + day);
   }
 
   public static long formatNow2Long()
   {
    return format2Long(new Date());
   }
 
   public static long formatNowAddSecond2Long(int second)
   {
     Calendar calendar = Calendar.getInstance(TIMEZONE);
     calendar.add(13, second);
    return format2Long(calendar.getTime());
   }
 
   public static long formatNowAddMinute2Long(int minute)
   {
    Calendar calendar = Calendar.getInstance(TIMEZONE);
    calendar.add(12, minute);
    return format2Long(calendar.getTime());
   }
 
   public static long formatNowAdHour2Long(int hour)
   {
    Calendar calendar = Calendar.getInstance(TIMEZONE);
     calendar.add(11, hour);
     return format2Long(calendar.getTime());
   }
 
   public static Date parse(long format)
   {
   String str = String.valueOf(format);
    if (str.length() >= 8) {
      Calendar calendar = Calendar.getInstance(TIMEZONE);
      calendar.clear();
       calendar.set(1, Integer.parseInt(str.substring(0, 4)));
      calendar.set(2, Integer.parseInt(str.substring(4, 6)) - 1);
     calendar.set(5, Integer.parseInt(str.substring(6, 8)));
      if (str.length() >= 14) {
       calendar.set(11, Integer.parseInt(str.substring(8, 10)));
         calendar.set(12, Integer.parseInt(str.substring(10, 12)));
         calendar.set(13, Integer.parseInt(str.substring(12, 14)));
       }
       if (str.length() == 17) {
         calendar.set(14, Integer.parseInt(str.substring(14, 17)));
       }
       return calendar.getTime();
     }
     return null;
   }
 }