package com.itdan.imooc.o2o.enums;


/**
 * 店铺状态枚举类
 */
public enum  ShopStateEnum {

    CKECK(0,"审核中"),
    OFFLING(-1,"非法店铺"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    INNER_EEROR(-1001,"内部错误"),
    NULL_SHOPID(-1002,"shopID为空"),
    NULL_SHOP(-1003,"shop为空");

    //结果状态
    private  int state;
    //状态标识
    private String stateInfo;


    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 根据传入的state值，返回相应的enum类型
     * @param state
     * @return
     */
    public static  ShopStateEnum getState(int state){
        for(ShopStateEnum stateEnum : values()){
            if(stateEnum.getState()==state){
                return stateEnum;
            }
        }
        return  null;

    }

    public int getState() {
        return state;
    }


    public String getStateInfo() {
        return stateInfo;
    }

}
