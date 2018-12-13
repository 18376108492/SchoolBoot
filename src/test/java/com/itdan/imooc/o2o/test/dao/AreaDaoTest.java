package com.itdan.imooc.o2o.test.dao;

import com.itdan.imooc.o2o.dao.AreaDAO;
import com.itdan.imooc.o2o.entity.Area;
import com.itdan.imooc.o2o.test.BaseTest;
import junit.runner.BaseTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * area测试类
 */


public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDAO areaDAO;
    @Test
    public void testGetAllArea () throws Exception{
      List<Area> list= areaDAO.getAllArea();
        System.out.println(list);
    }
}
