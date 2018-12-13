package com.itdan.imooc.o2o.controller.superadmin;


import com.itdan.imooc.o2o.entity.Area;
import com.itdan.imooc.o2o.service.AreaService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequestMapping("/superadmin")
public class AreaController {


    @Autowired
    private AreaService areaService;

      @RequestMapping(value="/getallarea" ,method = RequestMethod.GET)
      @ResponseBody
       private  Map<String , Object> getAllArea(){
        Map modelMap=new HashMap<String, Object>();
        try {
            List<Area> list= areaService.getAllArea();
            modelMap.put("rows" ,list);
            modelMap.put("totol" ,list.size());
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
        }
             return modelMap;
    }
}