package com.itdan.imooc.o2o.util;



/**
 * 存储路径工具类
 */
public class PathUtil {

    //获取文件的分隔符
    private static String separator=System.getProperty("file.separator");


    /**
     * 获取图片的绝对路径
     * @return
     */
    public  static  String getImgBasePath(){
        //获取根路径
        String os=System.getProperty("os.name");
        String basePath="";

        //如果是windo系统
        if(os.toLowerCase().startsWith("win")){
            basePath="D:/projectdev/image/";
        }else{
            basePath="home/dan/image/";
        }
       basePath= basePath.replace("/",separator);
       return basePath;
    }



    /**
     * 获取店铺文件的存储路径
     * @param shopId
     * @return
     */
    public static String getShopBasePath(Long shopId){
     String imagePath="upload/item/shop"+shopId+"/";
     return imagePath.replace("/",separator);

    }
}
