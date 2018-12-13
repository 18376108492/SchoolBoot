package com.itdan.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理request头信息工具类
 */
public class HttpServletRequestUtil {

    public  static  Integer getInt(HttpServletRequest request,String key){
        //根据key在request中取出值，并转化为int型
      try {
          return Integer.decode(request.getParameter(key));
      }catch (Exception e){
       return -1;
      }
    }

    public  static  Long getLong(HttpServletRequest request,String key){
        //根据key在request中取出值，并转化为long型
        try {
            return Long.decode(request.getParameter(key));
        }catch (Exception e){
            return -1L;
        }
    }

    public  static  Double getDouble(HttpServletRequest request,String key){
        //根据key在request中取出值，并转化为double型
        try {
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            return -1d;
        }
    }

    public  static  Boolean getBoolean(HttpServletRequest request,String key){
        //根据key在request中取出值，并转化为long型
        try {
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            return false;
        }
    }

    public  static  String getString(HttpServletRequest request,String key){
        try {

            String result=request.getParameter(key);
            if(result!=null){
                result=result.trim();//忽略前导空白和尾部空白
            }
            if("".equals(result)){
                result=null;
            }
            return  result;
        }catch (Exception e){
            return null;
        }
    }
}
