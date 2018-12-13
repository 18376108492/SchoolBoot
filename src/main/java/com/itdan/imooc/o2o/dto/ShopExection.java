package com.itdan.imooc.o2o.dto;

import com.itdan.imooc.o2o.entity.Shop;
import com.itdan.imooc.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * 店铺操作返回类
 */
public class ShopExection {

    //结果状态
    private  int state;
    //状态标识
    private String stateInfo;
    //店铺数量
    private int count;
    //shop列表（增删改时用到）
    private Shop shop;
    //shop列表（查询时会用到）
    private List<Shop> shopList;

    public ShopExection() {
    }

    /**
     * 店铺操作失败的构造器
     * @param shopExection
     */
    public ShopExection(ShopStateEnum shopExection) {
        this.state = shopExection.getState();
        this.stateInfo= shopExection.getStateInfo();
    }

    /**
     * 店铺操作成功的构造器
     * @param shopExection
     * @param shop
     */
    public ShopExection(ShopStateEnum shopExection,Shop shop) {
        this.state = shopExection.getState();
        this.stateInfo= shopExection.getStateInfo();
        this.shop=shop;
    }

    /**
     * 店铺操作成功的构造器
     * @param shopExection
     * @param shop
     */
    public ShopExection(ShopStateEnum shopExection,List<Shop> shopList) {
        this.state = shopExection.getState();
        this.stateInfo= shopExection.getStateInfo();
        this.shopList=shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
