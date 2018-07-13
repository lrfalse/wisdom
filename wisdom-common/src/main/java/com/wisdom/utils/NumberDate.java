

package com.wisdom.utils;

import com.google.common.base.Preconditions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NumberDate {
    public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    private long numberTimes;

    public long getNumberTimes() {
        return this.numberTimes;
    }

    private NumberDate(long numberTimes) {
        Preconditions.checkArgument(String.valueOf(numberTimes).length() >= 4, "必须至少是4位数字：如2016");
        this.numberTimes = numberTimes;
    }

    public static NumberDate newNumberDate(long numberTimes) {
        return new NumberDate(numberTimes);
    }

    public static NumberDate newNumberDate(String strDate, String format) {
        Preconditions.checkNotNull(strDate);
        Preconditions.checkNotNull(format);
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        try {
            date = sdf.parse(strDate);
        } catch (ParseException var5) {
            throw new RuntimeException("无法转换的日期:" + strDate);
        }

        return newNumberDate(date);
    }

    public static NumberDate newNumberDate(Date date) {
        Preconditions.checkNotNull(date);
        return new NumberDate(DateUtils.format2Long(date, false));
    }

    public static NumberDate newNumberDateHasMillisecond(Date date) {
        Preconditions.checkNotNull(date);
        return new NumberDate(DateUtils.format2Long(date, true));
    }

    public String format(String format) {
        return this.numberTimes <= 0L ? "0" : DateUtils.format(this.toDate(), format);
    }

    public boolean before(NumberDate another, NumberDate.TransType transType) {
        Preconditions.checkNotNull(another);
        int n = 0;
        if (NumberDate.TransType.YEAR == transType) {
            n = 4;
        } else if (NumberDate.TransType.MONTH == transType) {
            n = 6;
        } else if (NumberDate.TransType.DAY == transType) {
            n = 8;
        } else if (NumberDate.TransType.HOUR == transType) {
            n = 10;
        } else if (NumberDate.TransType.MINUTE == transType) {
            n = 12;
        } else if (NumberDate.TransType.SECOND == transType) {
            n = 14;
        } else if (NumberDate.TransType.MILLISECOND == transType) {
            n = 17;
        }

        long me = Long.parseLong(String.valueOf(this.numberTimes).substring(0, n));
        long her = Long.parseLong(String.valueOf(another.getNumberTimes()).substring(0, n));
        return me < her;
    }

    public boolean after(NumberDate another, NumberDate.TransType transType) {
        Preconditions.checkNotNull(another);
        int n = 0;
        if (NumberDate.TransType.YEAR == transType) {
            n = 4;
        } else if (NumberDate.TransType.MONTH == transType) {
            n = 6;
        } else if (NumberDate.TransType.DAY == transType) {
            n = 8;
        } else if (NumberDate.TransType.HOUR == transType) {
            n = 10;
        } else if (NumberDate.TransType.MINUTE == transType) {
            n = 12;
        } else if (NumberDate.TransType.SECOND == transType) {
            n = 14;
        } else if (NumberDate.TransType.MILLISECOND == transType) {
            n = 17;
        }

        long me = Long.parseLong(String.valueOf(this.numberTimes).substring(0, n));
        long her = Long.parseLong(String.valueOf(another.getNumberTimes()).substring(0, n));
        return me > her;
    }

    public NumberDate minus(int n, NumberDate.TransType transType) {
        Preconditions.checkArgument(n > 0, "参数n必须大于0");
        return this.addOrMinus(-n, transType);
    }

    public NumberDate add(int n, NumberDate.TransType transType) {
        Preconditions.checkArgument(n > 0, "参数n必须大于0");
        return this.addOrMinus(n, transType);
    }

    private NumberDate addOrMinus(int n, NumberDate.TransType transType) {
        Calendar me = Calendar.getInstance();
        me.setTime(this.toDate());
        if (NumberDate.TransType.YEAR == transType) {
            me.add(1, n);
        } else if (NumberDate.TransType.MONTH == transType) {
            me.add(2, n);
        } else if (NumberDate.TransType.DAY == transType) {
            me.add(5, n);
        } else if (NumberDate.TransType.HOUR == transType) {
            me.add(10, n);
        } else if (NumberDate.TransType.MINUTE == transType) {
            me.add(12, n);
        } else if (NumberDate.TransType.SECOND == transType) {
            me.add(13, n);
        } else if (NumberDate.TransType.MILLISECOND == transType) {
            me.add(14, n);
        }

        return newNumberDate(me.getTime());
    }

    public Date toDate() {
        String str = String.valueOf(this.numberTimes);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        if (str.length() >= 4) {
            calendar.set(1, Integer.parseInt(str.substring(0, 4)));
        }

        if (str.length() >= 6) {
            calendar.set(2, Integer.parseInt(str.substring(4, 6)) - 1);
        }

        if (str.length() >= 8) {
            calendar.set(5, Integer.parseInt(str.substring(6, 8)));
        }

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

    public String toString() {
        return String.valueOf(this.numberTimes);
    }

    public String toString(NumberDate.TransType transType) {
        if (NumberDate.TransType.YEAR == transType) {
            return String.valueOf(this.numberTimes).substring(0, 4);
        } else if (NumberDate.TransType.MONTH == transType) {
            return String.valueOf(this.numberTimes).substring(0, 6);
        } else if (NumberDate.TransType.DAY == transType) {
            return String.valueOf(this.numberTimes).substring(0, 8);
        } else if (NumberDate.TransType.HOUR == transType) {
            return String.valueOf(this.numberTimes).substring(0, 10);
        } else if (NumberDate.TransType.MINUTE == transType) {
            return String.valueOf(this.numberTimes).substring(0, 12);
        } else if (NumberDate.TransType.SECOND == transType) {
            return String.valueOf(this.numberTimes).substring(0, 14);
        } else if (NumberDate.TransType.MILLISECOND == transType) {
            String str = String.valueOf(this.numberTimes);
            return str.length() == 17 ? String.valueOf(this.numberTimes).substring(0, 17) : this.numberTimes + "000";
        } else {
            return String.valueOf(this.numberTimes);
        }
    }

    public static enum TransType {
        MILLISECOND,
        SECOND,
        MINUTE,
        HOUR,
        DAY,
        MONTH,
        YEAR;

        private TransType() {
        }
    }
}
