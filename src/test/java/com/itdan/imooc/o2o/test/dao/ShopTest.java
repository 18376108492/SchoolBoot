package com.itdan.imooc.o2o.test.dao;

import com.itdan.imooc.o2o.dao.ShopDAO;
import com.itdan.imooc.o2o.entity.Area;
import com.itdan.imooc.o2o.entity.PersonInfo;
import com.itdan.imooc.o2o.entity.Shop;
import com.itdan.imooc.o2o.entity.ShopCategory;
import com.itdan.imooc.o2o.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ShopTest extends BaseTest {

    @Autowired
    private ShopDAO shopDAO;

    @Test
    public void testAddShop () throws Exception{
        Shop shop=new Shop();
        PersonInfo owner=new PersonInfo();
        ShopCategory shopCategory =new ShopCategory();
        Area area=new Area();
        owner.setUserId(1L);
        shopCategory.setShopCategoryId(1L);
        area.setAreaId(1L);

        shop.setArea(area);
        shop.setOwner(owner);
        shop.setShopCategory(shopCategory);
        shop.setShopName("啤酒");
        shop.setShopDesc("德国扎啤");
        shop.setAdvice("审核中");

        int rows=shopDAO.addShop(shop);
        System.out.println(rows);
    }


    /*
    *  Preparing: UPDATE tb_shop SET shop_name=?, shop_desc=?, shop_addr=?,
     *  phone=?, create_time=?, last_edit_time=?, enable_status=?, shop_category_id=?, owner_id=? where shop_id=?
==> Parameters: 鲜美河鱼(String), text(String), upload\item\shop6\2018121021203086973.jpg(String), 183462544(String), 2018-12-10 21:20:30.501(Timestamp), 2018-12-10 21:20:30.501(Timestamp), 0(Integer), 2(Long), 2(Long), 6(Long)
<==    Updates: 1
    * */

    @Test
    public void testUpdateShop () throws Exception{
        Shop shop=new Shop();
        shop.setShopId(1L);
        shop.setShopName("四川凤爪");
        shop.setShopDesc("麻辣鲜香");
        shop.setAdvice("审核通过");
        shop.setShopImg("这不是地址");
        shop.setShopAddr("这不是图片");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        int rows=shopDAO.updateShop(shop);
        System.out.println(rows);
    }


}
