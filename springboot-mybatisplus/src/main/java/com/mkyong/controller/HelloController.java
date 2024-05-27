package com.mkyong.controller;


import com.mkyong.component.AbsComponent;
import com.mkyong.model.UserDTO;
import com.mkyong.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;


/**
 * @author mengka
 * @Date 2022-02-10 19:25
 */
@Slf4j
@RestController
@RequestMapping(path = "/t1")
public class HelloController {

//    @Autowired
//    private SpringUtil springUtil;

    @Autowired
    private ApplicationContext applicationContext;

    /**
     *  http://127.0.0.1:8083/t1/t1?id=2
     *
     * @param id
     * @return
     */
    @RequestMapping(path = "/t1", method = RequestMethod.GET)
    public String t1(@RequestParam(value = "id")Integer id) {
        log.info("---t1--- {}");

        String[] beans = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beans);
        for(String bean:beans){
            log.info("---------, "+bean +" of type:: "+applicationContext.getBean(bean).getClass());
        }

        AbsComponent component = null;
        if(id == 1) {
            component = (AbsComponent)SpringUtil.getBean("TAA_COMPONENT");
        }else{
            component = (AbsComponent)SpringUtil.getBean("TBB_COMPONENT");
        }
        return component.foo();
    }
}
