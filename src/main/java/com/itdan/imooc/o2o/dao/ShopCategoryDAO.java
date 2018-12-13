package com.itdan.imooc.o2o.dao;

import com.itdan.imooc.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品分类持久层接口
 */
public interface ShopCategoryDAO {

    /**
     * 查询商品类型操作
     * @param shopCategory
     * @return
     */
    List<ShopCategory> queryShopCategory(
            @Param("shopCategoryCondition")ShopCategory shopCategoryCondition);

}
