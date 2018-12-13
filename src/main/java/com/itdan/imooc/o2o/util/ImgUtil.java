package com.itdan.imooc.o2o.util;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 图片处理和封装工具类(thumbnailator)
 */
public class ImgUtil {

    //获取水印文件的绝对值路径
    private static String basePath=Thread.currentThread()
            .getContextClassLoader().getResource("").getPath();
    //随机对象
    private static Random romdan=new Random();
    //日期模板
    private static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");


    public static String generateThumbnail(InputStream thumbnail,String fileName,
                                           String tagerAddr){

         String  realFileName=getRandomFileName();//生成随机文件名
         String extension=getFileExtension(fileName);//截取传入文件的后缀
         makeDirPath(tagerAddr);//自动生成目标文件

         String relativeAddr=tagerAddr+realFileName+extension;//拼接完整的目标文件路径
         File dest=new File(PathUtil.getImgBasePath()+relativeAddr);//生成新的目标文件
       try {
           //ImgIO.read是去读取水印的地址,后面的0.25f参数是透明度,
           // outputQuality表示压缩文件,toFile表示文件输出的地址
           Thumbnails.of(thumbnail)
                   .size(400,400)
                   .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f)
                   .outputQuality(0.8f)
                   .toFile(dest);
       }catch (Exception e){
            e.printStackTrace();
       }
           return relativeAddr;
    }

    /**
     * 生成随机文件名：当前的年月日+五位随机数
     * @return
     */
     public static String  getRandomFileName(){
        int randomNum=romdan.nextInt(89999)+10000;
        String date=simpleDateFormat.format(new Date());
        return date+randomNum;
     }

    /**
     * 获取文件流的扩展名
     * @param fileName
     * @return
     */
     private static  String getFileExtension(String fileName){
         String  extension=fileName.substring(fileName.lastIndexOf("."));
         return  extension;
     }

    /**
     *创建目标文件所涉及到的目录，例如"/home/dan/image/xxx.jpg"
     * 那么 home dan image 三个文件夹将自动生成
     * @param tagerAddr
     */
    private static  void  makeDirPath(String tagerAddr){
        String realFilePath=PathUtil.getImgBasePath()+tagerAddr;
        File dirFile=new File(realFilePath);
        //判断文件夹是否存在
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }

     }




    public static void main(String[] args) throws Exception{


        //ImgIO.read是去读取水印的地址,后面的0.25f参数是透明度,
        // outputQuality表示压缩文件,toFile表示文件输出的地址
        Thumbnails.of(new File("C:/Users/WIN8/Pictures/timg.jpg"))
                .size(991,987)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath+"/watermark.jpg")), 0.25f)
                .outputQuality(0.8f)
                .toFile(new File("C:/Users/WIN8/Pictures/xiaohuangren.jpg"));

    }


}
