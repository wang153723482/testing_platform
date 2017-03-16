package com.wangc.comm;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangchao on 2017/3/10.
 */
public class StringUtils {
    
    public static String dd(int d,int n){
        // 0 代表前面补充0     
        // n 代表补0后的总长度     
        // d 代表参数为正数型     
        return String.format("%0"+n+"d", d);
    }
    
    /**
     * @param root 创建目录的父路径
     * @return 当前时间的文件夹路径,结果无斜杠。返回的结果应该是：/2017/03
     */
    public static String  creAndGetDir(String root){
        String path = new StringBuilder(File.separator)
                .append(getDate("yyyy"))
                .append(File.separator)
                .append(getDate("MM"))
                .toString();
        String fullPath = root+path;
        File file = new File(fullPath);
        file.mkdirs();
        return path;
    }

    /**
     * @return 返回 20170313 这样格式的字符串
     */
    public static String getDate(){
        return getDate("yyyyMMdd");
    }

    /**
     * @param format yyyy MM dd HH hh mm ss
     * @return 返回对应格式的字符串
     */
    public static String getDate(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }
    
    public static void main(String[] args){
        System.out.println( dd(1,9) );
        System.out.println(getDate("yyyyMMddHHmmssSSS"));
        System.out.println(getDate("yyyyMMddHHmmssSSS"));
        
        
        System.out.println( creAndGetDir("z:/") );
        
        
        System.out.println( Math.random()*100000 );
        
        
    }
    
}
