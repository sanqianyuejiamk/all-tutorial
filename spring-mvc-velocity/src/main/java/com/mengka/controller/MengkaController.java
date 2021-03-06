package com.mengka.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/*

  http://127.0.0.1:8080/mk/topic.do
 * User: mengka
 * Date: 15-5-1-下午7:52
 */
@Controller
@RequestMapping("/mk")
public class MengkaController {

    static final Logger logger = LogManager.getLogger(MengkaController.class);


    @RequestMapping(value = "/topic.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String topic(ModelMap map, HttpServletRequest request,
                        @RequestParam(required = false) String groupName) {
        logger.error("--------, a view of topic.vm..");
        logger.info("--------, #megnka# info ..");
        return "mengka/topic";
    }



    @RequestMapping(value = "/saveTopic.do", method = { RequestMethod.GET, RequestMethod.POST })
    public String saveTopic(ModelMap map, HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            Integer type = Integer.parseInt(request.getParameter("type"));
            String groupId = request.getParameter("groupId");
            String ctype = request.getParameter("ctype");
            String topic = request.getParameter("topic");
            String userName = request.getParameter("userName");




            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status","1");
            jsonObject.put("message","保存成功!");
            map.put("result",jsonObject.toJSONString());
        }
        catch (Throwable e) {
            logger.error("saveTopic.do error! ",e);
        }
        return "mengka/success";
    }
}
