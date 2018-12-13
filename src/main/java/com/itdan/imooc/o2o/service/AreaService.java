package com.itdan.imooc.o2o.service;

import com.itdan.imooc.o2o.entity.Area;

import java.util.List;

/**
 * 区域业务逻辑接口
 */
public interface AreaService {

    /**
     * 获取所有区域列表
     * @return
     */
    List<Area> getAllArea();

}
