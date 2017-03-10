package com.wangc.comm;

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
    
    public static void main(String[] args){
        System.out.println( dd(1,9) );
    }
    
}
