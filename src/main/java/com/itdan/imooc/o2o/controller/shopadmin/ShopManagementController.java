package com.itdan.imooc.o2o.controller.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdan.imooc.o2o.dto.ShopExection;
import com.itdan.imooc.o2o.entity.Area;
import com.itdan.imooc.o2o.entity.PersonInfo;
import com.itdan.imooc.o2o.entity.Shop;
import com.itdan.imooc.o2o.entity.ShopCategory;
import com.itdan.imooc.o2o.enums.ShopStateEnum;
import com.itdan.imooc.o2o.service.AreaService;
import com.itdan.imooc.o2o.service.ShopCategoryService;
import com.itdan.imooc.o2o.service.ShopService;
import com.itdan.imooc.o2o.util.CodeUtil;
import com.itdan.imooc.o2o.util.HttpServletRequestUtil;
import com.itdan.imooc.o2o.util.ImgUtil;
import com.itdan.imooc.o2o.util.PathUtil;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商城信息控制
 */

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;


    /**
     * 更新商品信息时，根据商品的id查询出数据并将数据返回页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/getShopById",method = {RequestMethod.GET})
    @ResponseBody
    private Map<String,Object>  getShopById(HttpServletRequest request){
        Map<String,Object> modelMap=new HashMap<>();
        Long shopId=HttpServletRequestUtil.getLong(request,"shopId");
        if(shopId>-1){
            try {
               Shop shop=shopService.queryById(shopId);//获取要修改的店铺信息
               List<Area> areaList=areaService.getAllArea();//获取区域信息
               modelMap.put("area",areaList);
               modelMap.put("shop",shop);
               modelMap.put("success",true);
            }catch (Exception e){
                modelMap.put("errorMsg","修改店铺出错"+e.getMessage());
                modelMap.put("success",false);
            }
        }else {
            modelMap.put("errorMsg","修改店铺出错:传入的shopIp小于0");
            modelMap.put("success",false);
        }
        return modelMap;
    }

    /**
     * 查询相应的区域和商品类型信息，并返回给Json数据给注册页面
     * @return
     */
    @RequestMapping(value = "/getshopinitinfo",method = {RequestMethod.GET})
    @ResponseBody
    public  Map<String ,Object> getshopinitinfo(){
        Map<String,Object> modelMap=new HashMap<>();
        List<ShopCategory> shopCategoryList=new ArrayList<>();
        List<Area> areaList=new ArrayList<>();

        //获取信息
        try {
            shopCategoryList=shopCategoryService.queryShopCategory(new ShopCategory());
            areaList=areaService.getAllArea();

            //成功
            modelMap.put("shopCategoryList",shopCategoryList);
            modelMap.put("areaList",areaList);
            modelMap.put("success", true);
        }catch (Exception e){
            //如果失败
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            return modelMap;
        }

       return modelMap;
    }


    /**
     * 注册店铺
     * @param request
     * @return
     */
    @RequestMapping(value = "/registerShopMsg", method = {RequestMethod.POST})
    @ResponseBody
    private Map<String, Object> registerShopMsg(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //验证码校验码
        if(!CodeUtil.checkVerifyCode(request)){
            map.put("success", false);
            map.put("errorMsg", "验证码输入错误");
            return map;
        }
        //1.接收并转化相应的参数，包括店铺信息和图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        System.out.println(shopStr);
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errorMsg", e.getMessage());
            return map;
        }
        //接收店铺图片
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            map.put("success", false);
            map.put("errorMsg", "上传图片失败");
        }

        //2.注册店铺
        if (shop != null && shopImg != null) {
            PersonInfo owner =(PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
             //添加店铺
            //CommonsMultipartFile.getInputStream,通过InPutStream转成File
            try {
                ShopExection exection=shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                if (exection.getState()==ShopStateEnum.CKECK.getState()){
                    map.put("success",true);
                    //该用户可以操作的店铺列表
                    List<Shop>shopList=(List<Shop>) request.getSession().getAttribute("shopList");
                     if(shopList==null || shopList.size()==0){
                       shopList=new ArrayList<Shop>();
                       shopList.add(exection.getShop());
                       request.getSession().setAttribute("shopList",shopList);
                     }else {
                         shopList.add(exection.getShop());
                         request.getSession().setAttribute("shopList",shopList);
                     }
                }else {
                    map.put("success", false);
                    map.put("errorMsg", exection.getStateInfo());
                }
                return map;
            }catch (Exception e){
                map.put("success", false);
                map.put("errorMsg", e.getMessage());
            }
        } else {
            map.put("success", false);
            map.put("errorMsg", "注册店铺失败");
        }

        //3.返回结果

        return map;
    }


    /**
     * 更新店铺
     * @param request
     * @return
     */
    @RequestMapping(value = "/modiftShopMsg", method = {RequestMethod.POST})
    @ResponseBody
    private Map<String, Object> modiftShopMsg(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //验证码校验码
        if(!CodeUtil.checkVerifyCode(request)){
            map.put("success", false);
            map.put("errorMsg", "验证码输入错误");
            return map;
        }
        //1.接收并转化相应的参数，包括店铺信息和图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        System.out.println(shopStr);
        ObjectMapper mapper = new ObjectMapper(); // create once, reuse
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errorMsg", e.getMessage());
            return map;
        }
        //接收店铺图片
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        }

        //2.修改店铺
        if (shop != null &&shop.getShopId()!=null ) {
            //从session中获取登入用户
            PersonInfo owner =(PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            //修改店铺
            //CommonsMultipartFile.getInputStream,通过InPutStream转成File
            try {
                ShopExection exection;
                if(shopImg==null){
                    exection=  shopService.modiftShop(shop,null,null);//如果传入的图片为空，只对其shop对象做处理
                }else {
                    exection=shopService.modiftShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
                }
                if (exection.getState()==ShopStateEnum.SUCCESS.getState()){
                    map.put("success",true);
                }else {
                    map.put("success", false);
                    map.put("errorMsg", exection.getStateInfo());
                }
                return map;
            }catch (Exception e){
                map.put("success", false);
                map.put("errorMsg", e.getMessage());
            }
        } else {
            map.put("success", false);
            map.put("errorMsg", "注册店铺,请输入id");
        }

        //3.返回结果

        return map;
    }


    /**
     * CommonsMultipartFile.getInputStream,通过InPutStream转成File
     * @param inputStream
     * @param file
     */
   /* private  static  void inputStream2File(InputStream inputStream,File file){
        FileOutputStream outputStream = null;
        try {
            outputStream=new FileOutputStream(file);
            int bytesRead=0;
             byte[]buffer=new  byte[1024];
             while ((bytesRead=inputStream.read(buffer))!=-1){
                outputStream.write(buffer,0,bytesRead);
             }
        }catch (Exception e){
            throw  new RuntimeException("调用nputStream2File出错："+e.getMessage());
        }finally {
            //关闭资源
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
                if(outputStream!=null){
                    outputStream.close();
                }
            }catch (Exception e){
                throw  new RuntimeException("关闭资源出错："+e.getMessage());
            }
        }
    }*/

}
