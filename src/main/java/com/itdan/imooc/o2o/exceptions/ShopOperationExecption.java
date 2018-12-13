package com.itdan.imooc.o2o.exceptions;

import org.apache.ibatis.javassist.SerialVersionUID;

/**
 * 店铺操作异常类
 */
public class ShopOperationExecption extends  RuntimeException{

    public ShopOperationExecption(String message) {
        super(message);
    }

    public ShopOperationExecption(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopOperationExecption(Throwable cause) {
        super(cause);
    }
}
