package com.itdan.imooc.o2o.dao;

import com.itdan.imooc.o2o.entity.Shop;

/**
 *
 */
public interface ShopDAO {

    /**
     * 根据id查询商品操作
     * @param shopId
     * @return
     */
    Shop queryById(Long shopId);

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
