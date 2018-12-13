package com.itdan.imooc.o2o.service.impl;

import com.itdan.imooc.o2o.dao.ShopDAO;
import com.itdan.imooc.o2o.dto.ShopExection;
import com.itdan.imooc.o2o.entity.Shop;
import com.itdan.imooc.o2o.enums.ShopStateEnum;
import com.itdan.imooc.o2o.exceptions.ShopOperationExecption;
import com.itdan.imooc.o2o.service.ShopService;
import com.itdan.imooc.o2o.util.ImgUtil;
import com.itdan.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

/**
 * 商铺业务逻辑类
 */
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDAO shopDAO;

    @Override
    @Transactional//事务标签
    //如果抛出的异常不是RuntimeException或者其子类时，事务是不会回滚的。
    public ShopExection addShop(Shop shop,
                                InputStream shopImgInputStream,String fileName) {

        //判断传入的参数
        if (shop == null) {
            return new ShopExection(ShopStateEnum.NULL_SHOP);
        }
        try {
            //给店铺赋值
            shop.setEnableStatus(0);//0代表审核中
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            //调用dao保存店铺
            int returnRow=shopDAO.addShop(shop);
            if(returnRow<=0){
                throw  new RuntimeException("店铺创建失败.");
            }else {
                //如果店铺添加成功，将图片存储到相应的地方
                //判断shopImg是否为空
                if(fileName!=null){
                    try {
                        addShopImg(shop,shopImgInputStream,fileName);
                    }catch (Exception e){
                        throw new RuntimeException("addShopImg error: "
                                + e.getMessage());
                    }
                    //更新店铺图片地址
                    int  row= shopDAO.updateShop(shop);
                    if(row<=0){
                        throw  new RuntimeException("图片创建失败.");
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException("updateShopImg error: "
                    + e.getMessage());
        }

        return new ShopExection(ShopStateEnum.CKECK,shop);
    }

    private static  void addShopImg(Shop shop,
                                    InputStream shopImgInputStream,String FileName){
        //获取图片的相对目录路径
        String baseAddr= PathUtil.getShopBasePath(shop.getShopId());
        String shopImg=ImgUtil.generateThumbnail(shopImgInputStream,FileName,baseAddr);
        //将保存后的路径保存shop对象中
        shop.setShopImg(shopImg);
    }


}
