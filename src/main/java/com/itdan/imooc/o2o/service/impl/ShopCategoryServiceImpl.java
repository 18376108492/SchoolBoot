package com.itdan.imooc.o2o.service.impl;

import com.itdan.imooc.o2o.dao.ShopCategoryDAO;
import com.itdan.imooc.o2o.entity.ShopCategory;
import com.itdan.imooc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品分类业务逻辑实现类
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    @Override
    public List<ShopCategory> queryShopCategory(ShopCategory shopCategoryCondition) {

        List<ShopCategory> list=shopCategoryDAO.queryShopCategory(
                shopCategoryCondition);

        return list;
    }
}
