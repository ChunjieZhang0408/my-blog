package com.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Desc DateUtils
 * @Author ZhangChunjie
 * @Date 2020/1/12 11:22
 * @Version 1.0
 */
public class DateUtils {

    public static String nowTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static String formatDay(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        return dateFormat.format(date);
    }
}
