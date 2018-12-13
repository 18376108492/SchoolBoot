package com.itdan.imooc.o2o.dao;

import com.itdan.imooc.o2o.entity.Area;

import java.util.List;

public interface AreaDAO {
    /**
     * 获取所有区域列表
     * @return
     */
    List<Area> getAllArea();

}