package com.itdan.imooc.o2o.dao;

import com.itdan.imooc.o2o.entity.Shop;

/**
 *
 */
public interface ShopDAO {

    /**
     * 添加商铺操作
     * @param shop
     * @return
     */
    int addShop(Shop shop);

    /**
     * 更新店铺操作
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
