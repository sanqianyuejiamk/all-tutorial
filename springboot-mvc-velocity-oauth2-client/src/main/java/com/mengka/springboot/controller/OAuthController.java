package com.mengka.springboot.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huangyy
 * @version farabbit2.0, 2019/4/17
 * @since farabbit2.0
 */
@Log4j2
@Controller
public class OAuthController {

//    @Autowired
//    private ConsumerTokenServices consumerTokenServices;
//


    @Autowired
    private UserInfoTokenServices userInfoTokenServices;

    @RequestMapping(value="/invalidateToken", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, String> logout(@RequestParam(name = "access_token") String accessToken) {
//        log.debug("Invalidating token {}", accessToken);
//        consumerTokenServices.revokeToken(accessToken);



        Map<String, String> ret = new HashMap<String, String>();
        ret.put("access_token", accessToken);
        return ret;
    }
}