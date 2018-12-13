package com.itdan.imooc.o2o.service;

import com.itdan.imooc.o2o.dto.ShopExection;
import com.itdan.imooc.o2o.entity.Shop;
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

}

