package com.itdan.imooc.o2o.controller.superadmin;

import com.itdan.imooc.o2o.entity.Shop;
import com.itdan.imooc.o2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;

/**
 * 商铺逻辑控制
 */
@Controller
@RequestMapping(value = "/shopadmin",method = {RequestMethod.GET} )
public class ShopController {

    /**
     *跳转店铺注册页面
     * @return
     */
    @RequestMapping(value = "/registerShop")
    public String registerShop(){
        return "shop/shopRegister";
    }
}
