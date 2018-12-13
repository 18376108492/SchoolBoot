package com.itdan.imooc.o2o.service.impl;

import com.itdan.imooc.o2o.dao.AreaDAO;
import com.itdan.imooc.o2o.entity.Area;
import com.itdan.imooc.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区域业务逻辑类
 */
@Service
public class AreaServiceImpl  implements AreaService {

    @Autowired
    private AreaDAO areaDAO;

    @Override
    public List<Area> getAllArea() {
        List<Area> list=areaDAO.getAllArea();
        return list;
    }
}
