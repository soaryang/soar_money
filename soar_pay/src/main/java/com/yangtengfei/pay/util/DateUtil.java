package com.yangtengfei.pay.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtil {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static String calendarToString(Calendar calendar,String format) {
        if (calendar == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(format);// 设置你想要的格式
        String dateStr = df.format(calendar.getTime());
        return dateStr;
    }
}
