package com.itdan.imooc.o2o.test.dao;

import com.itdan.imooc.o2o.dao.ShopCategoryDAO;
import com.itdan.imooc.o2o.entity.ShopCategory;
import com.itdan.imooc.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryDAOTest extends BaseTest {

    @Autowired
    private ShopCategoryDAO shopCategoryDAO;

    @Test
    public void testShopCategoryDAO () throws Exception{
        ShopCategory shopCategory=new ShopCategory();
        ShopCategory parentShopCategory=new ShopCategory();
        parentShopCategory.setShopCategoryId(1L);
        shopCategory.setShopCategoryId(4L);
        shopCategory.setParent(parentShopCategory);
        List<ShopCategory> list= shopCategoryDAO.queryShopCategory(shopCategory);
// SELECT shop_category_id, shop_category_name, shop_category_desc,
// shop_category_img, priority, create_time, last_edit_time, parent_id
// FROM tb_shop_category WHERE parent_id=? ORDER BY priority DESC
        System.out.println(list);
    }
}
