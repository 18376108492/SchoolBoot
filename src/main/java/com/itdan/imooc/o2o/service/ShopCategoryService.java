package com.itdan.imooc.o2o.service;

import com.itdan.imooc.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类业务逻辑接口
 */
public interface ShopCategoryService {

    /**
     * 查询商品类型操作
     * @param shopCategory
     * @return
     */
    List<ShopCategory> queryShopCategory(
            @Param("shopCategoryCondition")ShopCategory shopCategoryCondition);

}
