package com.itdan.imooc.o2o.test.service;

import com.itdan.imooc.o2o.dao.ShopDAO;
import com.itdan.imooc.o2o.dto.ShopExection;
import com.itdan.imooc.o2o.entity.Area;
import com.itdan.imooc.o2o.entity.PersonInfo;
import com.itdan.imooc.o2o.entity.Shop;
import com.itdan.imooc.o2o.entity.ShopCategory;
import com.itdan.imooc.o2o.enums.ShopStateEnum;
import com.itdan.imooc.o2o.service.ShopService;
import com.itdan.imooc.o2o.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;
    @Test
    public void testShopSericeTest () throws Exception{

        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        ShopCategory shopCategory =new ShopCategory();
        Area area=new Area();
        owner.setUserId(1L);
        shopCategory.setShopCategoryId(1L);
        area.setAreaId(2L);

        shop.setArea(area);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试店铺3");
        shop.setShopDesc("测试产品3");
        shop.setShopAddr("test3");
        shop.setPhone("test3");
        shop.setShopImg("test3");
        shop.setEnableStatus(ShopStateEnum.CKECK.getState());
        shop.setAdvice("审核中");
        File file=new File("C:/Users/WIN8/Pictures/timg.jpg");
        InputStream is=new FileInputStream(file);
        ShopExection shopExection= shopService.addShop(shop,is,file.getName());
        Assert.assertEquals(ShopStateEnum.CKECK.getState(), shopExection.getState());



    }


}
