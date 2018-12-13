package com.itdan.imooc.o2o.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试配置实体类
 */
@RunWith(SpringJUnit4ClassRunner.class)
//spring配置文件
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class BaseTest {

}
