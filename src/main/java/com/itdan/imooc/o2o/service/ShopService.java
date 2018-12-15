package com.itdan.imooc.o2o.service;

import com.itdan.imooc.o2o.dto.ShopExection;
import com.itdan.imooc.o2o.entity.Shop;
import com.itdan.imooc.o2o.exceptions.ShopOperationExecption;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * 商铺业务逻辑接口
 */
public interface ShopService {

    /**
     * 添加店铺操作
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExection addShop(Shop shop, InputStream shopImgInputStream,String fileName);

    /**
     * 根据id查询商品操作
     * @param shopId
     * @return
     */
    Shop queryById(Long shopId);

    /**
     * 更新店铺信息包括对店铺图片的跟新处理处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExection modiftShop(Shop shop, InputStream shopImgInputStream,String fileName)  throws ShopOperationExecption;
}

